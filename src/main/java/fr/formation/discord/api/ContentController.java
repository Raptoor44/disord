package fr.formation.discord.api;

import fr.formation.discord.models.Content;
import fr.formation.discord.models.Message;
import fr.formation.discord.models.User;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.MessageRepository;
import fr.formation.discord.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class ContentController {

    @Autowired
    private ChannelRepository cRepo;
    @Autowired
    private MessageRepository mRepo;
    @Autowired
    private UserRepository uRepo;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Content greeting(Message message, @Header("channelId") Long channelId, @Header("username") String username) throws Exception {
        String space = ": ";

        User user = uRepo.findByUsername(username).orElseThrow();

        message.setUser(user);

        message.setChannel(cRepo.findById(channelId.intValue()).orElse(null));
        mRepo.save(message);
        return new Content(HtmlUtils.htmlEscape(user.getUsername() + space + message.getContent()));
    }
}
