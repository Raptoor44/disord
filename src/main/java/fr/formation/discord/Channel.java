package fr.formation.discord;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Channel {
    private Long id;
    private String name;
    private List<Message> messages;

}
