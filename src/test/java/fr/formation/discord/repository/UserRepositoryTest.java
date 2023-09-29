package fr.formation.discord.repository;

import fr.formation.discord.models.User;
import fr.formation.discord.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Test
    void getByUsername(){
        Optional<User> optResult = this.repo.findByUsername("a");

        Assertions.assertTrue(optResult.isPresent());
    }
}