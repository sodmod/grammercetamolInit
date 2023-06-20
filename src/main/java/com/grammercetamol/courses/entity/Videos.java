package com.grammercetamol.courses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Table
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Videos {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(nullable = false)
    private String publicId;
    @Column(nullable = false)
    private String videosUrl;

    public Videos(String publicId, String videosUrl) {
        this.publicId = publicId;
        this.videosUrl = videosUrl;
    }
}
