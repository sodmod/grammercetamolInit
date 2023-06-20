package com.grammercetamol.courses.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudMethods {

    Map<String, Object> uploadVideosToCloudinary(MultipartFile file);

    Object streamVideos(String publicId) throws IOException;

}
