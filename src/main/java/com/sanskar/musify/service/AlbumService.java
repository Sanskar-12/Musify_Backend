package com.sanskar.musify.service;


import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;

public interface AlbumService {
    
    Album addAlbum(AlbumRequest req);

}
