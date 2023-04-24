package com.grammercetamol.cloudinary;

import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadsService {

    public List<String> uploadMultipleVideos(String json) throws IOException {
        JSONArray jsonArray = new JSONArray(json);
        List<String> publicIds = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String videoUrl = jsonObject.getString("url");
            String publicId = jsonObject.getString("public_id");
            InputStream inputStream = new URL(videoUrl).openStream();
//            UploadResult uploadResult = cloudi
        }
        return null;
    }
}
