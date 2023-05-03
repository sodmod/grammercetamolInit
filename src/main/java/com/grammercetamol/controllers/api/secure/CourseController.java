package com.grammercetamol.controllers.api.secure;

import com.grammercetamol.controllers.services.CourseControllerService;
import com.grammercetamol.courses.WhitelistedCourse;
import com.grammercetamol.payload.request.CoursesUploadDTO;
import com.grammercetamol.payload.request.WhitelistDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/cloudinary/")
public class CourseController {
    private final CourseControllerService courseControllerService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadVideos(@ModelAttribute @NonNull CoursesUploadDTO coursesUploadDTO) {
//        courseControllerService.getCourses(coursesUploadDTO);
        return courseControllerService.getUploadCourses(coursesUploadDTO);
    }

    public ResponseEntity<?> retrieveCourses() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> whiteListCourse(@RequestBody WhitelistDTO whitelistDTO) {
        courseControllerService.whiteListCourse(whitelistDTO);
        return null;
    }

    @GetMapping(value = "/getWhitelistedCourses")
    public ResponseEntity<?> whiteListedCourses(@RequestBody String email) {
        List<WhitelistedCourse> whitelistedCourses = courseControllerService.whiteListedCourses(email);
        return ResponseEntity.ok().body(whitelistedCourses);
    }

    @DeleteMapping(value = "/deletewhitelistedcourse/{course_id}")
    public ResponseEntity<?> unwhitelistCourse(@PathVariable int course_id) {
        courseControllerService.unwhitelistCourse(course_id);
        return null;
    }
}
