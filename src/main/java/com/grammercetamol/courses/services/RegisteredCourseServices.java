package com.grammercetamol.courses.services;

import com.grammercetamol.courses.entity.Courses;
import com.grammercetamol.courses.entity.RegisteredCourses;
import com.grammercetamol.courses.repository.RegisteredCoursesRepository;
import com.grammercetamol.utilities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisteredCourseServices {
    private final RegisteredCoursesRepository registeredCoursesRepository;
    private RegisteredCourses registeredCourses;

    public void applyCouse(Courses courses, Users users) {
        registeredCourses = new RegisteredCourses(users, courses);
        registeredCoursesRepository.save(registeredCourses);
    }

    public List<RegisteredCourses> getListOfCourses(Users users) {
        return registeredCoursesRepository.findAllByUsers(users);
    }
}
