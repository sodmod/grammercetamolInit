package com.grammercetamol.courses;

import com.grammercetamol.utilities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
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
