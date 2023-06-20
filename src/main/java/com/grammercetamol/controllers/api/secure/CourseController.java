package com.grammercetamol.controllers.api.secure;

import com.grammercetamol.controllers.services.CourseControllerService;
import com.grammercetamol.courses.entity.Courses;
import com.grammercetamol.courses.entity.WhitelistedCourse;
import com.grammercetamol.payload.request.CoursesUploadDTO;
import com.grammercetamol.payload.request.WhitelistDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/cloudinary/")
public class CourseController {
    private final CourseControllerService courseControllerService;

    /**
     * @param file
     * @param coursesUploadDTO
     * @return
     */

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadVideos(@RequestParam("file") @NonNull List<MultipartFile> file, @ModelAttribute @NonNull CoursesUploadDTO coursesUploadDTO) {
        return courseControllerService.uploadNewCourses(file, coursesUploadDTO);
    }


    /**
     * All API methods Bellow this comment is for students API
     */

    /**
     * @return this list of all courses available
     */
    @GetMapping(value = "/courses")
    public ResponseEntity<?> getAllCourses() {
        List<Courses> courses = courseControllerService.getAllCourses();
        return ResponseEntity.ok().body(courses);
    }

    /**
     * @param whitelistDTO
     * @return
     */
    @PostMapping(value = "/whitelist")
    public ResponseEntity<?> whiteListCourse(@RequestBody WhitelistDTO whitelistDTO) {
        courseControllerService.whiteListCourse(whitelistDTO);
        return null;
    }

    /**
     * @param email which serves as the studentId
     * @return gives the List of Courses as response
     */

    @GetMapping(value = "/getWhitelistedCourses")
    public ResponseEntity<?> whiteListedCourses(@RequestParam(value = "studentId") String email) {
        List<WhitelistedCourse> whitelistedCourses = courseControllerService.whiteListedCourses(email);
        return ResponseEntity.ok().body(whitelistedCourses);
    }

    /**
     * @param course_id it gets the courseId in integer value
     * @return @ResponseEntity<?> value
     */

    @DeleteMapping(value = "/deleteWhitelistedCourse/{course_id}")
    public ResponseEntity<?> deleteWhitelistedCourse(@PathVariable int course_id) {
        courseControllerService.unwhitelistCourse(course_id);
        return null;
    }


    /**
     * @param publicId
     * @return
     */
    @GetMapping(value = "/stream/{publicId}")
    public ResponseEntity<?> streamVideos(@PathVariable String publicId) {
        courseControllerService.streamVideos(publicId);
        return null;
    }

    /**
     * @param studentId
     * @param courseId
     * @return
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerCourse(@RequestBody String studentId, @RequestBody String courseId) {
        courseControllerService.applyCourse(courseId, studentId);
        return ResponseEntity.ok().body("registered successfully");
    }

    /**
     * @param studentId
     * @return
     */
    public ResponseEntity<?> getRegisteredCourses(@PathVariable String studentId) {
        return courseControllerService.getRegisteredCourses(studentId);
    }
}
