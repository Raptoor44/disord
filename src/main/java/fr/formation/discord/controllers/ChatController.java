package fr.formation.discord.controllers;


import fr.formation.discord.models.Channel;
import fr.formation.discord.models.Message;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.MessageRepository;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.MessageSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private List<Message> myMessages = new ArrayList<>();
    private Long channelIdCounter = 0L;
    private Long currentIdChannel = null;

    @Autowired
    private ChannelRepository cRepo;
    @Autowired
    private MessageRepository mRepo;
    @Autowired
    private UserRepository uRepo;

    @RequestMapping("/chathome")
    public String home(Model model, @RequestParam(required = false) Long channelId) {
        if (channelId != null) {
            Channel channel = cRepo.findById(channelId.intValue()).orElseThrow();
            if (channel != null) {
                model.addAttribute("myMessages", channel.getMessages());
                model.addAttribute("currentChannelId", channelId);
                model.addAttribute("currentChannelName", channel.getName());
            }
        } else {
            model.addAttribute("myMessages", myMessages);
        }
        model.addAttribute("channels", cRepo.findAll());
        return "page_chat";
    }

    @PostMapping("/createChannel")
    public String createChannel(@RequestParam("channelName") String channelName, Model model) {
        Channel newChannel = new Channel();
        newChannel.setName(channelName);
        cRepo.save(newChannel);
        model.addAttribute("channels", cRepo.findAll());
        return "redirect:/chathome?channelId=" + newChannel.getId();
    }
    @GetMapping("/changeChannel")
    public String changeChannel(@RequestParam("channelId") Long channelId, Model model) {
        currentIdChannel = channelId;
        List<Message> messages = mRepo.findByChannelId(channelId);
        List<Channel> channels = cRepo.findAll();
        model.addAttribute("myMessages", messages);
        return "redirect:/chathome?channelId=" + currentIdChannel;
    }
}



