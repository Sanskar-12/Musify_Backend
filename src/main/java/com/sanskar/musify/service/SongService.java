package com.sanskar.musify.service;

import com.sanskar.musify.document.Song;
import com.sanskar.musify.io.SongRequest;

import java.io.IOException;

public interface SongService {

    public Song addSong(SongRequest request) throws IOException;
}
