package com.grammercetamol.courses.repository;

import com.grammercetamol.courses.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
    Authors findByMail(String mail);

    boolean existsByMail(String authorMail);
}
