package com.grammercetamol.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class CoursesUploadDTO {
    private String courseName;
    private String authorName;
    private String authorMail;
    private List<MultipartFile> multipartFile;
}
