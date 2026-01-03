package com.sanskar.musify.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    // Upload file
    public String uploadFile(MultipartFile file) throws IOException;

    // Delete file
    public void deleteFile(String publicId) throws IOException;
}
