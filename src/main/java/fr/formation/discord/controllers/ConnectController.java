package fr.formation.discord.controllers;

import fr.formation.discord.models.User;
import fr.formation.discord.models.UserLoaded;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.UserSignUpAndConnect;
import fr.formation.discord.security.JwtUtil;
import fr.formation.discord.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/connect")
    public String signup(Model model) {
        return "connect";
    }

    @PostMapping("/connect")
    public String postConnect(UserSignUpAndConnect request, Model model) {

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/chathome";
        }

        catch (BadCredentialsException e) {
            return "popupFaildedConnect";
        }


    }
}
