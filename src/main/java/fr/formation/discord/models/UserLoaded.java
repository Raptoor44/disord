package fr.formation.discord.models;

import fr.formation.discord.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class UserLoaded {
        public static User user;
}
