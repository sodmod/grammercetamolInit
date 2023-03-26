package com.grammercetamol.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(
            name = "firstname",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "lastname",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "othername",
            nullable = false
    )
    private String otherName;
    @Column(
            unique = true,
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "mobile_number",
            nullable = false
    )
    private String phoneNumber;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "role",
            nullable = false
    )
    private String role;


    public Users(String firstName, String lastName, String otherName, String email, String phoneNumber, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

}