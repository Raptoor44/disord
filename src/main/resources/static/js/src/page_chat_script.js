$(document).ready(function () {
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/gs-guide-websocket'
    });


    const form = document.querySelector('#video-form');
    const videoDiv = document.querySelector('#video-player');
    const videoScreen = document.querySelector('#video-screen');

    let idChannel = $("#idChannel").val();

    let url = `http://localhost:8080/api/video/all?idChannel=${idChannel}`;

    fetch(url, {
        method: 'GET'
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Network error with http://localhost:8080/api/video/${videoName}");
        }
    }).then(data => {
        const myVids = document.querySelector('#your-videos');

        if (data.length > 0) {
            data.forEach(vid => {
                const li = document.createElement('li');
                const videoName = vid;

                // Création du lient pour voir le nom de la vidéo
                const link = document.createElement('a');
                link.innerText = videoName;
                link.style.marginRight = '1em';
                li.appendChild(link);


                // Création du bouton pour charger la vidéo
                const loadButton = document.createElement('button');
                loadButton.innerText = 'Charger la vidéo';
                li.appendChild(loadButton);

                // Ajoutez un gestionnaire d'événements pour le bouton "Charger la vidéo"
                loadButton.addEventListener('click', function () {
                    // Requête ajax pour récupérer la vidéo
                    fetch(`http://localhost:8080/api/video/${videoName}`)
                        .then(response => {
                            if (response.ok) {
                                return response.blob();
                            } else {
                                throw new Error('Network error with http://localhost:8080/api/video/${videoName}');
                            }
                        })
                        .then(blob => {
                            const videoUrl = URL.createObjectURL(blob);
                            videoScreen.src = videoUrl;
                            videoDiv.style.display = 'block';
                            document.querySelector('#now-playing').innerText = 'Lecture ' + videoName;
                        })
                        .catch(error => {
                            console.error('Fetch error: ' + error);
                        });
                });

                myVids.appendChild(li);
            });
        } else {
            myVids.innerHTML = 'No videos found';
        }
    }).catch(error => {
        console.error('Fetch error: ' + error);
    });

    // Ajoutez un gestionnaire d'événements de clic aux liens vidéo
    $('#your-videos a').click(function (event) {
        event.preventDefault(); // Empêcher le lien de naviguer

        const videoUrl = $(this).text(); // Obtenez le nom de la vidéo à partir du texte du lien

        // Effectuez une requête API pour récupérer et afficher la vidéo
        videoScreen.src = `http://localhost:8080/api/video/${videoUrl}`;
        videoDiv.style.display = 'block';
        document.querySelector('#now-playing').innerText = 'Lecture ' + videoUrl;
    });

    form.addEventListener('submit', ev => {
        ev.preventDefault();

        let data = new FormData(form);
        data.append('channelId', $("#idChannel").val());

        fetch('http://localhost:8080/api/video', {
            method: 'POST',
            body: data
        }).then(result => result.text()).then(_ => {
            window.location.reload();
        });
    });


    const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

    stompClient.onConnect = (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', (greetings) => {
            $("#chat-box ul").append("<li class='message-box'>" + JSON.parse(greetings.body).content + "</li>");
        });
    };

    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };

    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        } else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }

    function connect() {
        stompClient.activate();
    }

    $("#message-form").on('submit', function (e) {
        e.preventDefault();
        var content = $("#content").val();
        var channelId = $("#idChannel").val();
        var headers = {
            "channelId": channelId
        };

        stompClient.publish({
            destination: "/hello",
            headers: headers,
            body: JSON.stringify(content)
        });
        $("#content").val("");
    });
    connect();
    $( "#connect" ).click(() => connect());
});