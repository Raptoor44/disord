package fr.formation.discord.security;

import fr.formation.discord.models.User;
import fr.formation.discord.repo.UserRepository;
import fr.formation.discord.services.UserLoaded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Assurez-vous d'avoir une interface UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implémentez la logique pour récupérer les détails de l'utilisateur depuis votre base de données.
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }

        UserLoaded.user = user;
        return new CustomUserDetails(user);
    }
}
