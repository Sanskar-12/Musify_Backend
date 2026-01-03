package com.sanskar.musify.service.impl;

import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;
import com.sanskar.musify.repository.AlbumRepository;
import com.sanskar.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album addAlbum(AlbumRequest req) {
        return null;
    }
}
