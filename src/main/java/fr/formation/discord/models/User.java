package fr.formation.discord.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "[user]")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    // Ajoutez un constructeur vide
    public User() {
    }

    // Ajoutez un constructeur avec username et password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
