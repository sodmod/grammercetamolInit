package com.grammercetamol.courses.repository;

import com.grammercetamol.courses.Courses;
import com.grammercetamol.courses.WhitelistedCourse;
import com.grammercetamol.utilities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiteListedCourseRepository extends JpaRepository<WhitelistedCourse, Long> {
    List<WhitelistedCourse> findByUsers(Users users);

    WhitelistedCourse findByCourses(Courses courses);
}
