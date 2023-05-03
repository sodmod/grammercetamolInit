package com.grammercetamol.courses;

import com.grammercetamol.utilities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table
public class Whitelist {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToMany
    @JoinColumn(name = "courses_id")
    private Set<Courses> courses;

    private Whitelist(Users users, Set<Courses> courses) {
        this.users = users;
        this.courses = courses;
    }
}
