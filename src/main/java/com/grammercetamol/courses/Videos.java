package com.grammercetamol.courses;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Table
@Entity
@NoArgsConstructor
@Data
public class Videos {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(nullable = false)
    private String publicId;
    @Column(nullable = false)
    private String videosUrl;

    public Videos(String publicId, String videosUrl) {
        this.publicId = publicId;
        this.videosUrl = videosUrl;
    }
}
