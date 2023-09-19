package fr.formation.discord.controller;

import fr.formation.discord.Message;
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

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("myMessages", myMessages);
        return "page_home";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String content) {
        Message message = new Message();
        message.setContent(content);
        myMessages.add(message);
        return "redirect:/home";
    }
}

