package com.grammercetamol.courses.entity;

import com.grammercetamol.utilities.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class WhitelistedCourse {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToMany
    @JoinColumn(name = "courses_id")
    private List<Courses> courses;

    public WhitelistedCourse(Users users, List<Courses> courses) {
        this.users = users;
        this.courses = courses;
    }
}
