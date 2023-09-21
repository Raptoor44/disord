package fr.formation.discord.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "channel")
    private List<Message> messages;

    // Ajoutez un constructeur vide
    public Channel() {
    }
}
