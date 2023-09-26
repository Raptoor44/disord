package fr.formation.discord.repo;

import fr.formation.discord.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User>  findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
