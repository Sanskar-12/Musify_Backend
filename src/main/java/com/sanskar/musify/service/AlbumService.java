package com.sanskar.musify.service;


import com.sanskar.musify.document.Album;
import com.sanskar.musify.io.AlbumRequest;

import java.io.IOException;

public interface AlbumService {

    Album addAlbum(AlbumRequest req) throws IOException;

}
