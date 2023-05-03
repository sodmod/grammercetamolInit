package com.grammercetamol.courses.cloudinary;

import com.cloudinary.Cloudinary;
import com.grammercetamol.exceptions.CourseUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public Map<String, Object> uploadVideosToCloudinary(MultipartFile file) {
        try {

            Map<String, Object> params = new HashMap<>();
            String public_id = UUID.randomUUID().toString();
            params.put("public_id", public_id);
            params.put("resource_type", "auto");
            params.put("folder", "my-folder");
//            params.put("", "");

            Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), params);
            return result;
        } catch (IOException e) {
            throw new CourseUploadException("error uploading the video");
        }

    }

    public void retrieveVideos() {
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(String.valueOf(multipartFile.getBytes()));
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
