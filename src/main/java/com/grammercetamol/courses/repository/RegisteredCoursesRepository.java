package com.grammercetamol.courses.repository;

import com.grammercetamol.courses.entity.RegisteredCourses;
import com.grammercetamol.utilities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredCoursesRepository extends JpaRepository<RegisteredCourses, Long> {
    List<RegisteredCourses> findAllByUsers(Users users);
}
