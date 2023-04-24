package com.grammercetamol.cloudinary;

import com.grammercetamol.utilities.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table
@Entity
public class Images {

    @Id
    private Long id;
    private String images;
    @OneToOne
    @JoinTable(
            name = "images_users",
            joinColumns = @JoinColumn(
                    name = "image_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id"
            )
    )
    private Users users;

    public Images(String images, Users users) {
        this.images = images;
        this.users = users;
    }

}
