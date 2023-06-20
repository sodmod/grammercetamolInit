package com.grammercetamol.courses.repository;

import com.grammercetamol.courses.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    Optional<Courses> findByCourseId(String courseId);
}
