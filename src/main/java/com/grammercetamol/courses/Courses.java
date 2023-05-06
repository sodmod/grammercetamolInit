package com.grammercetamol.courses;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false, name = "course_name")
    private String courseName;
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
    }
}
