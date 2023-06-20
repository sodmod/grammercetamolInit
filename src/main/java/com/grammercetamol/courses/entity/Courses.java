package com.grammercetamol.courses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long Id;
    @Column(nullable = false, name = "course_name")
    private String courseName;
    @Column(
            nullable = false,
            name = "course_id",
            unique = true
    )
    private String courseId;
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Authors authors;
    @OneToMany
    @JoinColumn(name = "videos_id")
    private List<Videos> videos;

    public Courses(String courseName, Authors authors, List<Videos> videos) {
        this.courseName = courseName;
        this.authors = authors;
        this.videos = videos;
        this.courseId = UUID.randomUUID().toString();
    }
}
