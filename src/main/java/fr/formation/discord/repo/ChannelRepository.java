package fr.formation.discord.repo;

import fr.formation.discord.models.Channel;
import fr.formation.discord.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
