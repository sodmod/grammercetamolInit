package com.grammercetamol.controllers.services;

import com.grammercetamol.courses.cloudinary.CloudinaryService;
import com.grammercetamol.courses.entity.*;
import com.grammercetamol.courses.repository.AuthorRepository;
import com.grammercetamol.courses.repository.CoursesRepository;
import com.grammercetamol.courses.repository.VideosRepository;
import com.grammercetamol.courses.repository.WhiteListedCourseRepository;
import com.grammercetamol.courses.services.RegisteredCourseServices;
import com.grammercetamol.exceptions.CourseException;
import com.grammercetamol.exceptions.UsersException;
import com.grammercetamol.payload.request.CoursesUploadDTO;
import com.grammercetamol.payload.request.WhitelistDTO;
import com.grammercetamol.utilities.Users;
import com.grammercetamol.utilities.repositories.UsersRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

/**
 * @author Badmus Sodiq
 */

@Service
@RequiredArgsConstructor
public class CourseControllerService {
    private final CloudinaryService cloudinaryService;
    private final CoursesRepository coursesRepository;
    private final AuthorRepository authorRepository;
    private final VideosRepository videosRepository;
    private final WhiteListedCourseRepository whiteListedCourseRepository;
    private final UsersRepositories usersRepositories;
    private final RegisteredCourseServices registeredCourseServices;


    /**
     * @param files
     * @param coursesUploadDTO
     * @return
     */
    public ResponseEntity<?> uploadNewCourses(List<MultipartFile> files, CoursesUploadDTO coursesUploadDTO) {

        /**
         * This handles uploading courses
         * Only the ADMIN can have access to this method
         * This will generate the video url and public_id
         * It accepts the Videos and the author info
         */
        List<Videos> videosArrayList = new ArrayList<>();
        if (authorRepository.existsByMail(coursesUploadDTO.getAuthorMail())) {
            for (MultipartFile multiple : files) {
                Map res = cloudinaryService.uploadVideosToCloudinary(multiple);
                if (res.get("public_id").toString().isBlank() || res.get("url").toString().isBlank()) {
                    return ResponseEntity.noContent().build();
                } else {
                    Videos videos =
                            videosRepository.save(
                                    new Videos(
                                            res.get("public_id").toString(),
                                            res.get("url").toString()
                                    ));
                    videosArrayList.add(videos);
                }
            }
            Authors authors = authorRepository.findByMail(coursesUploadDTO.getAuthorMail());
            Courses courses =
                    new Courses(
                            coursesUploadDTO.getCourseName(),
                            authors, videosArrayList
                    );
            coursesRepository.save(courses);
        } else {
            for (MultipartFile multiple : files) {
                Map res = cloudinaryService.uploadVideosToCloudinary(multiple);
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


    /**
     * @return
     */
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }


    public void whiteListCourse(WhitelistDTO whitelistDTO) {
        Users users = usersRepositories.findByEmail(
                whitelistDTO.getEmail()
        ).orElseThrow(
                () -> new UsersException("user not found")
        );

        Courses courses = coursesRepository.findByCourseId(
                whitelistDTO.getCourseID()
        ).orElseThrow(
                () -> new CourseException("course not found")
        );

        WhitelistedCourse whitelistedCourse = new WhitelistedCourse(users, List.of(courses));

        whiteListedCourseRepository.save(whitelistedCourse);


    }

    public List<WhitelistedCourse> whiteListedCourses(String userEmail) {
        Users users = usersRepositories.findByEmail(userEmail).orElseThrow(() -> new UsersException("user not found"));
        return whiteListedCourseRepository.findAllByUsers(users);
    }

    public void unwhitelistCourse(int course_id) {
        whiteListedCourseRepository.findByCourses(
                coursesRepository.findById(
                        (long) course_id).orElseThrow(
                        () -> new CourseException("course not found")
                )
        );
    }


    /**
     * @param publicId
     * @return TODO: Not yet tested!!!
     * This method will handle video stream
     */
    public ResponseEntity<?> streamVideos(String publicId) {
        try {
            InputStreamResource inputStreamResource = cloudinaryService.streamVideos(publicId);
            HttpHeaders httpHeaders = new HttpHeaders();
            if (inputStreamResource != null) {
                httpHeaders.setContentType(APPLICATION_OCTET_STREAM);
            }
            return ResponseEntity.ok().headers(httpHeaders).body(inputStreamResource);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * @param courseId
     * @param studentId
     */
    public void applyCourse(String courseId, String studentId) {
        Courses courses = coursesRepository.findByCourseId(courseId)
                .orElseThrow(
                        () -> new CourseException(
                                "course not found"
                        )
                );

        Users users = usersRepositories.findByEmail(studentId)
                .orElseThrow(
                        () -> new UsersException(
                                "user not found"
                        )
                );

        registeredCourseServices.applyCouse(courses, users);
    }

    public ResponseEntity<?> getRegisteredCourses(String studentId) {
        Users users = usersRepositories.findByEmail(studentId).orElseThrow(() -> new UsersException("user not found"));
        List<RegisteredCourses> registeredCoursesList = registeredCourseServices.getListOfCourses(users);
        return ResponseEntity.ok().body(registeredCoursesList);
    }


}
