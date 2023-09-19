package fr.formation.discord.controllers;

import fr.formation.discord.models.User;
import fr.formation.discord.models.UserSingleton;
import fr.formation.discord.request.UserSignUpAndConnect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }
    @PostMapping("/signup")
    public String postSignUp(UserSignUpAndConnect request, Model model) {
        User user = new User(request.getUsername(), request.getPassword());
        UserSingleton users = new UserSingleton();

        UserSingleton.users.add(user);

        return "home";
    }

}
