package com.grammercetamol.securities.refreshToken;

import com.grammercetamol.utilities.Users;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name = "refreshToken")
public class RefreshToken {

    @Id
    @GeneratedValue(
            strategy = AUTO
    )
    private long id;
    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private Users users;
    @Column(
            nullable = false,
            unique = true
    )
    private String token;
    @Column(
            nullable = false
    )
    private Instant expireDate;
}
