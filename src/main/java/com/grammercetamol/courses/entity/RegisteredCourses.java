package com.grammercetamol.courses.entity;

import com.grammercetamol.utilities.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class RegisteredCourses {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @ManyToOne
    private Users users;
    @OneToOne
    @JoinColumn
    private Courses registeredCourses;

    public RegisteredCourses(Users users, Courses courses) {
        this.users = users;
        this.registeredCourses = courses;
    }
}
