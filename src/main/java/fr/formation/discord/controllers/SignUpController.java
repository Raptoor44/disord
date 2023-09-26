package fr.formation.discord.controllers;

import fr.formation.discord.models.User;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.UserSignUpAndConnect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UserRepository uRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup-post")
    public String postSignUp(UserSignUpAndConnect request, Model model) {
        User utilisateur = new User();

        BeanUtils.copyProperties(request, utilisateur);

        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        uRepo.save(utilisateur);

        return "home";
    }

}
