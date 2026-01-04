package com.sanskar.musify.controller;

import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumListResponse;
import com.sanskar.musify.io.AlbumRequest;
import com.sanskar.musify.service.AlbumService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/api/get/all/albums")
    public AlbumListResponse getAllAlbums() throws IOException {
        List<Album> albums = albumService.getAllAlbums();
        AlbumListResponse resp = new AlbumListResponse();
        resp.setSuccess(true);
        resp.setAlbums(albums);

        return resp;
    }

    @DeleteMapping("/api/delete/album/{id}")
    public ResponseEntity removeAlbum(@PathVariable String id) {
        try {
            Boolean removed = albumService.removeAlbum(id);
            if (removed) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
