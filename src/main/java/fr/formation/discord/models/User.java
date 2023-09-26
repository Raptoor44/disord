package fr.formation.discord.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}