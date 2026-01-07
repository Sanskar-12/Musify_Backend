package com.sanskar.musify.service;

import com.sanskar.musify.document.Song;
import com.sanskar.musify.io.SongListResponse;
import com.sanskar.musify.io.SongRequest;

import java.io.IOException;

public interface SongService {

    Song addSong(SongRequest request) throws IOException;

    SongListResponse getAllSongs() throws IOException;

    Boolean removeSong(String id) throws IOException;
}
