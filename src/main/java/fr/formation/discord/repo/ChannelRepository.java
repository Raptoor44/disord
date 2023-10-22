package fr.formation.discord.repo;

import fr.formation.discord.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

}
