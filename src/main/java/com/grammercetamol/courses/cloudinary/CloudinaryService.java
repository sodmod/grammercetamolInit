package com.grammercetamol.courses.cloudinary;

import com.cloudinary.Cloudinary;
import com.grammercetamol.exceptions.CourseUploadException;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService implements CloudMethods {
    private final Cloudinary cloudinary;

    /**
     * This method is used to upload videos into cloudinary
     */
    @Override
    public Map uploadVideosToCloudinary(MultipartFile file) {
        try {

            Map<String, Object> params = new HashMap<>();
            String public_id = UUID.randomUUID().toString();
            params.put("public_id", public_id);
            params.put("resource_type", "auto");
            params.put("folder", "my-folder");
            return cloudinary.uploader().uploadLarge(file.getBytes(), params);
        } catch (IOException e) {
            throw new CourseUploadException("error uploading the video");
        }
    }

    /*This method is used to stream videos from cloudinary which was uploaded
     * */
    @Override
    public InputStreamResource streamVideos(String publicId) throws IOException {
        String videoUrl = cloudinary.url().resourceType("video").generate(publicId);
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(videoUrl).build();
        Response response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            return null;
        } else {
            InputStream inputStream = response.body().byteStream();
            return new InputStreamResource(inputStream);
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
