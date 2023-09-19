package fr.formation.discord.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageSendRequest {
    private String username;
    private String content;
}
