package com.sanskar.musify.controller;

import com.sanskar.musify.document.Song;
import com.sanskar.musify.io.AlbumRequest;
import com.sanskar.musify.io.SongListResponse;
import com.sanskar.musify.io.SongRequest;
import com.sanskar.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/api/add/song")
    public ResponseEntity addSong(@RequestPart("request") String requestString,
                                  @RequestPart("audio") MultipartFile audioFile,
                                  @RequestPart("image") MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SongRequest songRequest = null;

            songRequest = objectMapper.readValue(requestString, SongRequest.class);
            songRequest.setImage(imageFile);
            songRequest.setFile(audioFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(songService.addSong(songRequest));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/get/all/songs")
    public ResponseEntity getAllSongs() {
        try {
            return ResponseEntity.ok(songService.getAllSongs());
        } catch (Exception e) {
            return ResponseEntity.ok(new SongListResponse(false, null));
        }
    }

}
