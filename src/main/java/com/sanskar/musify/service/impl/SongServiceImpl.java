package com.sanskar.musify.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sanskar.musify.document.Song;
import com.sanskar.musify.io.SongRequest;
import com.sanskar.musify.repository.SongRepository;
import com.sanskar.musify.service.CloudinaryService;
import com.sanskar.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Song addSong(SongRequest request) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(request.getFile().getBytes(),
                ObjectUtils.asMap("resource_type", "video"));
        String imgUrl = cloudinaryService.uploadFile(request.getImage());

        Double durationSeconds = (Double) uploadResult.get("duration");
        String duration = formatDuration(durationSeconds);

        Song song = convertToEntity(request, imgUrl, uploadResult.get("secure_url").toString(), duration);

        return songRepository.save(song);
    }

    private String formatDuration(Double durationSeconds) {
        if (durationSeconds == null || durationSeconds < 0) return "00:00";

        int totalSeconds = durationSeconds.intValue();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    private Song convertToEntity(SongRequest req, String img, String audio, String duration) {
        return Song.builder()
                .name(req.getName())
                .description(req.getDescription())
                .album(req.getAlbum())
                .image(img)
                .file(audio)
                .duration(duration)
                .build();
    }
}
