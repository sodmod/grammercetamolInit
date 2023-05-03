package com.grammercetamol.controllers.api.secure;

import com.grammercetamol.controllers.services.CourseUploadControllerService;
import com.grammercetamol.payload.request.CoursesUploadDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/cloudinary/")
public class CourseUploadController {
    private final CourseUploadControllerService courseUploadControllerService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadVideos(@ModelAttribute @NonNull CoursesUploadDTO coursesUploadDTO) {
//        courseUploadControllerService.getCourses(coursesUploadDTO);
        return courseUploadControllerService.getUploadCourses(coursesUploadDTO);
    }

    public ResponseEntity<?> retrieveCourses() {
        return null;
    }
}
