package com.grammercetamol.courses.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String cloud_name = "dk9uxcpvr";
    private final String api_key = "292934753593799";
    private final String api_secret = "9TGvliMjB1jrsqfcwXLdJV3N_e8";

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloud_name);
        config.put("api_key", api_key);
        config.put("api_secret", api_secret);
        return new Cloudinary(config);
    }
}
