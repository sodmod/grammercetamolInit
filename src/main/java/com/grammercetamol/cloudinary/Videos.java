package com.grammercetamol.cloudinary;

import com.grammercetamol.utilities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Table
@Entity
@NoArgsConstructor
@Data
public class Videos {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
    private String videosUrl;

    @OneToOne
    @JoinTable(
            name = "videosUrl_users",
            joinColumns = @JoinColumn(
                    name = "videosUrl_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id"
            )
    )
    private Users users;

    public Videos(String videosUrl, Users users) {
        this.videosUrl = videosUrl;
        this.users = users;
    }

}
