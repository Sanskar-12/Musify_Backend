package com.sanskar.musify.service.impl;

import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;
import com.sanskar.musify.repository.AlbumRepository;
import com.sanskar.musify.service.AlbumService;
import com.sanskar.musify.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Album addAlbum(AlbumRequest req) throws IOException {

        String imgUrl = cloudinaryService.uploadFile(req.getImgUrl());

        Album newAlbum = convertToEntity(req, imgUrl);

        return albumRepository.save(newAlbum);
    }

    @Override
    public List<Album> getAllAlbums() throws IOException {
        return albumRepository.findAll();
    }

    public Album convertToEntity(AlbumRequest request, String imgUrl) {
        return Album.builder()
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .imgUrl(imgUrl)
                .build();
    }
}
