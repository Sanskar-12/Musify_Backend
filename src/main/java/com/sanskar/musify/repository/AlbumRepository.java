package com.sanskar.musify.repository;

import com.sanskar.musify.document.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
