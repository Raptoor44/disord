package fr.formation.discord.controllers;

import fr.formation.discord.Content;
import fr.formation.discord.models.Message;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ContentController {

    @Autowired
    private ChannelRepository cRepo;
    @Autowired
    private MessageRepository mRepo;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Content greeting(Message message, @Header("channelId") Long channelId) throws Exception {
        String space = ": ";
        message.setUser(UserLoaded.user);
        message.setChannel(cRepo.findById(channelId.intValue()).orElse(null));
        mRepo.save(message);
        return new Content(HtmlUtils.htmlEscape( message.getUser().getUsername() + space + message.getContent()));
    }
}
