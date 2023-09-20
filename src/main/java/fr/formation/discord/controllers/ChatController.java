package fr.formation.discord.controllers;

import fr.formation.discord.Channel;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.request.MessageSendRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.formation.discord.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    private List<Message> myMessages = new ArrayList<>();
    private Map<Long, Channel> channels = new HashMap<>();
    private Long channelIdCounter = 0L;
    private Long currentIdChannel = null;

    @RequestMapping("/chathome")
    public String home(Model model, @RequestParam(required = false) Long channelId) {
        if (channelId != null) {
            Channel channel = channels.get(channelId);
            if (channel != null) {
                model.addAttribute("myMessages", channel.getMessages());
                model.addAttribute("currentChannelId", channelId);
                model.addAttribute("currentChannelName", channel.getName());
            }
        } else {
            model.addAttribute("myMessages", myMessages);
        }
        model.addAttribute("channels", channels.values());
        return "page_chat";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam(required = false) Long currentChannelId, MessageSendRequest request) {
        Message message = new Message();
        message.setUsername(UserLoaded.user.getUsername());
        message.setContent(request.getContent());
        currentChannelId = currentIdChannel;
        if (currentChannelId != null) {
            Channel channel = channels.get(currentChannelId);
            if (channel != null) {
                channel.getMessages().add(message);
            }
        } else {
            myMessages.add(message);
        }
        return "redirect:/chathome" + (currentChannelId != null ? "?channelId=" + currentChannelId : "");
    }
    @PostMapping("/createChannel")
    public String createChannel(@RequestParam("channelName") String channelName) {
        Channel newChannel = new Channel();
        newChannel.setId(channelIdCounter++);
        newChannel.setName(channelName);
        newChannel.setMessages(new ArrayList<>());
        channels.put(newChannel.getId(), newChannel);
        currentIdChannel = newChannel.getId();
        return "redirect:/chathome?channelId=" + newChannel.getId();
    }

    @PostMapping("/changeChannel")
    public String changeChannel(@RequestParam("channelId") Long channelId) {
        currentIdChannel = channelId;
        return "redirect:/chathome?channelId=" + channelId;
    }
}



