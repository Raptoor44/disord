package fr.formation.discord.security;
import fr.formation.discord.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repoUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        fr.formation.discord.models.User user = this.repoUtilisateur
                .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.withUsername(username)
            .password(user.getPassword())
            .build();
    }
}
