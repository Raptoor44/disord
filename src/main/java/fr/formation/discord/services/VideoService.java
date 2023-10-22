package fr.formation.discord.services;

import fr.formation.discord.Exceptions.VideoAlreadyExistsException;
import fr.formation.discord.Exceptions.VideoNotFoundException;
import fr.formation.discord.models.Video;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements IVideoService{

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Video getVideo(String name) {
        if(!videoRepository.existsByName(name)){
            throw new VideoNotFoundException();
        }
        return videoRepository.findByName(name);
    }

    @Override
    public List<Video> getVideoByChannelId(Long channelId) {
        if(!videoRepository.existsByChannelId(channelId)){
            throw new VideoNotFoundException();
        }
        return videoRepository.findByChannelId(channelId);
    }

    @Override
    public void saveVideo(MultipartFile file, String name, long idChannel) throws IOException {
        if(videoRepository.existsByName(name)){
            throw new VideoAlreadyExistsException();
        }

        Video newVid = new Video(name, file.getBytes() ,UserLoaded.user, channelRepository.getReferenceById(Math.toIntExact(idChannel)));
        videoRepository.save(newVid);
    }

    @Override
    public List<String> getAllVideoNamesByIdChannel(long idChannel) {
        return videoRepository.getAllEntryNames(idChannel);
    }
}
