package com.sanskar.musify.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumRequest {

    private String id;
    private String name;
    private String description;
    private String bgColor;
    private MultipartFile imgUrl;
}
