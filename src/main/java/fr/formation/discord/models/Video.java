package fr.formation.discord.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Video{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true)
    private String name;

    @Column(length = 999999999)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id") // Spécifiez le nom de la colonne pour la relation
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id") // Spécifiez le nom de la colonne pour la relation
    private Channel channel;

    public Video(String name, byte[] data, User user, Channel channel) {
        this.name = name;
        this.data = data;
        this.user = user;
        this.channel = channel;
    }
}