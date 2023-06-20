package com.grammercetamol.courses.repository;

import com.grammercetamol.courses.entity.Courses;
import com.grammercetamol.courses.entity.WhitelistedCourse;
import com.grammercetamol.utilities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiteListedCourseRepository extends JpaRepository<WhitelistedCourse, Long> {
    List<WhitelistedCourse> findAllByUsers(Users users);

    WhitelistedCourse findByCourses(Courses courses);
}
