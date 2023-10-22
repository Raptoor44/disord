package fr.formation.discord.services;

import fr.formation.discord.models.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IVideoService {
    Video getVideo(String name);

    List<Video> getVideoByChannelId(Long channelId);

    void saveVideo(MultipartFile file, String name, long idChannel) throws IOException;


    List<String> getAllVideoNamesByIdChannel(long idChannel);
}
