package fr.formation.discord.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id") // Spécifiez le nom de la colonne pour la relation
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id") // Spécifiez le nom de la colonne pour la relation
    private Channel channel;

    // Ajoutez un constructeur vide
    public Message() {
    }

    // Ajoutez un constructeur avec content
    public Message(String content) {
        this.content = content;
    }
}