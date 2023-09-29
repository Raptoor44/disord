package fr.formation.discord.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageSendRequest {
    private String username;
    private String content;
}
