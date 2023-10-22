package fr.formation.discord.repo;

import fr.formation.discord.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByChannelId(Long channelId);
}

