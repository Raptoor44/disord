package fr.formation.discord.repo;

import fr.formation.discord.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    Video findByName(String name);

    boolean existsByName(String name);

    List<Video> findByChannelId(Long channelId);
    boolean existsByChannelId(Long channelId);

    @Query(nativeQuery = true, value="SELECT name FROM video WHERE channel_id = :idChannel")
    List<String> getAllEntryNames(@Param("idChannel") long idChannel);

}
