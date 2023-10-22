package fr.formation.discord.video;

import fr.formation.discord.models.Video;
import fr.formation.discord.repo.ChannelRepository;
import fr.formation.discord.repo.VideoRepository;
import fr.formation.discord.services.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class VideoServiceImplementationTest {

    @Mock
    VideoRepository VideoRepo;

    @Mock
    ChannelRepository channelRepository;

    @InjectMocks
    VideoService service;
    String testName = "myVid";
    @Test
    void getVideo() {
        Video expected = new Video(testName, null, null, null);
        when(VideoRepo.findByName(testName))
                .thenReturn(expected);
        when(VideoRepo.existsByName(testName))
                .thenReturn(true);
        Video actual = service.getVideo(testName);
        //assertEquals(expected, actual);
        verify(VideoRepo, times(1)).existsByName(testName);
        verify(VideoRepo, times(1)).findByName(testName);
    }

    @Test
    @Sql(statements = "INSERT INTO Channel (id, name) VALUES (1, 'test')")
    void getAllVideoNames() {
        List<String> expected = List.of("myVid", "otherVid");
        when(VideoRepo.getAllEntryNames(1))
                .thenReturn(expected);
        List<String> actual = service.getAllVideoNamesByIdChannel(3);
        assertEquals(expected, actual);
        verify(VideoRepo, times(1)).getAllEntryNames(3);
    }
}
