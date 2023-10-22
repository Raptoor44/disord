package fr.formation.discord.api;

import fr.formation.discord.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @PostMapping()
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("channelId") String channelIdParam) throws IOException {

        long channelIdParsing = Long.parseLong(channelIdParam);

        videoService.saveVideo(file, name, channelIdParsing);
        return ResponseEntity.ok("Video saved successfully.");
    }

    @GetMapping("{name}")
    public ResponseEntity<ByteArrayResource> getVideoByName(@PathVariable("name") String name){
        return ResponseEntity
                .ok(new ByteArrayResource(videoService.getVideo(name).getData()));
    }

    @GetMapping("all")
    public ResponseEntity<List<String>> getAllVideoNames(@RequestParam("idChannel") String channelIdParam){

        long channelIdParsing = 0;
        if(!channelIdParam.isEmpty()){
            channelIdParsing = Long.parseLong(channelIdParam);
        }

        return ResponseEntity
                .ok(videoService.getAllVideoNamesByIdChannel(channelIdParsing));
    }

}