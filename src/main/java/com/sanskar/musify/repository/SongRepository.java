package com.sanskar.musify.repository;

import com.sanskar.musify.document.Song;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SongRepository extends MongoRepository<Song, String> {
}
