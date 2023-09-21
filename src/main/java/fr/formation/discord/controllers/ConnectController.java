package fr.formation.discord.controllers;

import fr.formation.discord.models.User;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.UserSignUpAndConnect;
import fr.formation.discord.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ConnectController {

    @Autowired
    private UserRepository uRepo;
    private UserService uService ;

    @GetMapping("/connect")
    public String signup(Model model) {
        return "connect";
    }

    @PostMapping("/connect")
    public String postSignUp(UserSignUpAndConnect request, Model model) {

        boolean flight = false;

        uService = new UserService(uRepo);
        User user = uService.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (user != null) {
            UserLoaded.user = user;
            flight = true;
        }

        if (flight) {
            return "redirect:/chathome";
        } else {
            return "popupFaildedConnect";
        }


    }
}
