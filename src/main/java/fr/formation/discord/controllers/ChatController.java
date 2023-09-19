package fr.formation.discord.controllers;

import fr.formation.discord.Message;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.request.MessageSendRequest;
import fr.formation.discord.request.UserSignUpAndConnect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private List<Message> myMessages = new ArrayList<>();

    @RequestMapping("/chathome")
    public String home(Model model) {
        model.addAttribute("myMessages", myMessages);
        return "page_chat";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(MessageSendRequest request) {
        Message message = new Message();
        message.setUsername(UserLoaded.user.getUsername());
        message.setContent(request.getContent());
        myMessages.add(message);
        return "redirect:/chathome";
    }
}

