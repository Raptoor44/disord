package fr.formation.discord.controllers;

import fr.formation.discord.models.Channel;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.MessageRepository;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.MessageSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.formation.discord.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(required = false) Long currentChannelId, MessageSendRequest request) {
        Message message = new Message();
        message.setContent(request.getContent());
        message.setUser(UserLoaded.user);

        currentChannelId = currentIdChannel;
        if (currentChannelId != null) {
            Channel channel = cRepo.findById(currentChannelId.intValue()).orElseThrow();
            if (channel != null) {
                message.setChannel(cRepo.findById(currentChannelId.intValue()).orElseThrow());
                mRepo.save(message);
            }
        } else {
            message.setChannel(cRepo.findById(currentChannelId.intValue()).orElseThrow());
            mRepo.save(message);
        }
        return "redirect:/chathome" + (currentChannelId != null ? "?channelId=" + currentChannelId : "");
    }
    @PostMapping("/createChannel")
    public String createChannel(@RequestParam("channelName") String channelName) {
        Channel newChannel = new Channel();
        newChannel.setId(channelIdCounter++);
        newChannel.setName(channelName);
        newChannel.setMessages(new ArrayList<>());
        cRepo.save(newChannel);
        currentIdChannel = newChannel.getId();
        return "redirect:/chathome?channelId=" + newChannel.getId();
    }

    @PostMapping("/changeChannel")
    public String changeChannel(@RequestParam("channelId") Long channelId) {
        currentIdChannel = channelId;
        return "redirect:/chathome?channelId=" + channelId;
    }
}



