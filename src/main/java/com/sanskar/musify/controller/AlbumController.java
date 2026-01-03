package com.sanskar.musify.controller;

import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;
import com.sanskar.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/api/add/album")
    @ResponseStatus(HttpStatus.CREATED)
    public Album addAlbum(@RequestPart("request") String request, @RequestPart("file") MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        AlbumRequest albumRequest = null;
        try {
            albumRequest = objectMapper.readValue(request, AlbumRequest.class);
            albumRequest.setImgUrl(file);
            return albumService.addAlbum(albumRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception occured while parsing the json: " + e.getMessage());
        }
    }
}
