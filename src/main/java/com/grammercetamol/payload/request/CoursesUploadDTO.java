package com.grammercetamol.payload.request;

import lombok.Data;

@Data
public class CoursesUploadDTO {
    private String courseName;
    private String authorName;
    private String authorMail;

}
