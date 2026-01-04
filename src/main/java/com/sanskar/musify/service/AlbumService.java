package com.sanskar.musify.service;


import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    Album addAlbum(AlbumRequest req) throws IOException;

    List<Album> getAllAlbums() throws IOException;

    Boolean removeAlbum(String id) throws IOException;

}
