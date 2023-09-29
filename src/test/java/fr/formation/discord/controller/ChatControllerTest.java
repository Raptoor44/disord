package fr.formation.discord.controller;

import fr.formation.discord.controllers.ChatController;
import fr.formation.discord.models.Channel;
import fr.formation.discord.models.Message;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.MessageRepository;
import fr.formation.discord.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import java.util.Collections;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ChatControllerTest {

    @Autowired
    private ChannelRepository cRepo;

    @Autowired
    private MessageRepository mRepo;

    @Mock
    private UserRepository uRepo;

    @Test
    public void testHomeWithChannelId() {
        // Créez un objet Model simulé
        Model model = mock(Model.class);

        // Créez un identifiant de canal simulé
        Long channelId = 1L;

        // Créez un canal simulé avec des messages
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName("General");
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        messages.add(new Message());
        channel.setMessages(messages);

        // Configurez le comportement du repo simulé (cRepo) pour renvoyer le canal simulé
        when(cRepo.findById(channelId.intValue())).thenReturn(Optional.of(channel));

        // Appelez la méthode home de ChatController
        ChatController chatController = new ChatController();
        chatController.home(model, channelId);

        // Vérifiez que les attributs du modèle ont été correctement définis
        verify(model, times(1)).addAttribute("myMessages", channel.getMessages());
        verify(model, times(1)).addAttribute("currentChannelId", channelId);
        verify(model, times(1)).addAttribute("currentChannelName", channel.getName());
        verify(model, times(1)).addAttribute("channels", cRepo.findAll());
    }

    @Test
    public void testHomeWithoutChannelId() {
        // Créez un objet Model simulé
        Model model = mock(Model.class);

        // Configurez le comportement du repo simulé (cRepo) pour renvoyer une liste vide
        when(cRepo.findById(-1)).thenReturn(null);

        // Appelez la méthode home de ChatController sans spécifier d'identifiant de canal
        ChatController chatController = new ChatController();
        chatController.home(model, null);

        // Vérifiez que les attributs du modèle ont été correctement définis
        verify(model, times(1)).addAttribute("myMessages", chatController.getMyMessages());
        verify(model, times(1)).addAttribute("channels", cRepo.findAll());
    }

    // Ajoutez d'autres tests unitaires au besoin
}
