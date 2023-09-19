package fr.formation.discord.controllers;

import fr.formation.discord.models.User;
import fr.formation.discord.models.UserSingleton;
import fr.formation.discord.request.UserSignUpAndConnect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ConnectController {

    @GetMapping("/connect")
    public String signup(Model model) {
        return "connect";
    }

    @PostMapping("/connect")
    public String postSignUp(UserSignUpAndConnect request, Model model) {

        boolean flight = false;

        for (User user : UserSingleton.users) {
            if (Objects.equals(user.getPassword(), request.getPassword()) && Objects.equals(user.getUsername(), request.getUsername())) {

                flight = true;
            }
        }
        if (flight) {
            return "home";
        } else {
            return "popupFaildedConnect";
        }


    }
}
