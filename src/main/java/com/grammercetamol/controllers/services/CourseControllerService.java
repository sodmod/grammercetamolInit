package com.grammercetamol.controllers.services;

import com.grammercetamol.courses.cloudinary.CloudinaryService;
import com.grammercetamol.courses.entity.Authors;
import com.grammercetamol.courses.entity.Courses;
import com.grammercetamol.courses.entity.Videos;
import com.grammercetamol.courses.entity.WhitelistedCourse;
import com.grammercetamol.courses.repository.AuthorRepository;
import com.grammercetamol.courses.repository.CoursesRepository;
import com.grammercetamol.courses.repository.VideosRepository;
import com.grammercetamol.courses.repository.WhiteListedCourseRepository;
import com.grammercetamol.exceptions.CourseException;
import com.grammercetamol.payload.request.CoursesUploadDTO;
import com.grammercetamol.payload.request.WhitelistDTO;
import com.grammercetamol.utilities.Users;
import com.grammercetamol.utilities.repositories.UsersRepositories;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseControllerService {
    private final CloudinaryService cloudinaryService;
    private final CoursesRepository coursesRepository;
    private final AuthorRepository authorRepository;
    private final VideosRepository videosRepository;
    private final WhiteListedCourseRepository whiteListedCourseRepository;
    private final UsersRepositories usersRepositories;

    private ResponseEntity<?> uploadNewCourses(@NonNull CoursesUploadDTO coursesUploadDTO) {
        /*
         * This handles uploading courses
         * Only the ADMIN can have access to this method
         * This will generate the video url and public_id
         * It accepts the Videos and the author info
         *  */
        List<Videos> videosArrayList = new ArrayList<>();
        if (authorRepository.existsByMail(coursesUploadDTO.getAuthorMail())) {
            for (MultipartFile multiple : coursesUploadDTO.getMultipartFile()) {
                Map<String, Object> res = cloudinaryService.uploadVideosToCloudinary(multiple);
                if ((res.get("public_id") == "" || res.get("public_id") == null) && (res.get("url") == "" || res.get("url") == null)) {
                    return ResponseEntity.noContent().build();
                }
                Videos videos =
                        videosRepository.save(
                                new Videos(
                                        res.get("public_id").toString(),
                                        res.get("url").toString()
                                ));
                videosArrayList.add(videos);
            }
            Authors authors = authorRepository.findByMail(coursesUploadDTO.getAuthorMail());
            Courses courses =
                    new Courses(
                            coursesUploadDTO.getCourseName(),
                            authors, videosArrayList
                    );
            coursesRepository.save(courses);
        } else {
            for (MultipartFile multiple : coursesUploadDTO.getMultipartFile()) {
                Map<String, Object> res = cloudinaryService.uploadVideosToCloudinary(multiple);
                Videos videos = videosRepository.save(new Videos(res.get("public_id").toString(), res.get("url").toString()));
                videosArrayList.add(videos);
            }
            Authors authors = authorRepository.save(
                    new Authors(
                            coursesUploadDTO.getAuthorName(),
                            coursesUploadDTO.getAuthorMail()
                    )
            );
            Courses courses =
                    new Courses(
                            coursesUploadDTO.getCourseName(),
                            authors, videosArrayList
                    );
            coursesRepository.save(courses);
        }
        return ResponseEntity.ok().body("uploaded successfully");
    }

    public ResponseEntity<?> getUploadCourses(@NonNull CoursesUploadDTO coursesUploadDTO) {
        return this.uploadNewCourses(coursesUploadDTO);
    }

    public void whiteListCourse(WhitelistDTO whitelistDTO) {
        Users users = usersRepositories.findByEmail(whitelistDTO.getEmail()).orElseThrow();
        Courses courses = coursesRepository.findById(whitelistDTO.getCourseID()).orElseThrow();
        WhitelistedCourse whitelistedCourse = new WhitelistedCourse(users, List.of(courses));

        whiteListedCourseRepository.save(whitelistedCourse);


    }

    public List<WhitelistedCourse> whiteListedCourses(String userEmail) {
        Users users = usersRepositories.findByEmail(userEmail).orElseThrow();
        return whiteListedCourseRepository.findByUsers(users);
    }

    public void unwhitelistCourse(int course_id) {
        whiteListedCourseRepository.findByCourses(
                coursesRepository.findById(
                        (long) course_id).orElseThrow(
                        () -> new CourseException("course not found")
                )
        );
    }
}
