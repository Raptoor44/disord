package fr.formation.discord.controllers;

import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.request.UserSignUpAndConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnectController {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ChannelRepository cRepo;

    @GetMapping("/connect")
    public String signup(Model model) {
        return "connect";
    }

    @PostMapping("/connect")
    public String postConnect(UserSignUpAndConnect request, Model model) {
        // On va demander à SPRING SECURITY d'authentifier l'utilisateur
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