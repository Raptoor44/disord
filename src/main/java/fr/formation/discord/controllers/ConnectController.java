package fr.formation.discord.controllers;


import fr.formation.discord.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnectController {

    @Autowired
    private UserRepository uRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/connect")
    public String signup(Model model) {

        return "connect";

    }
}
