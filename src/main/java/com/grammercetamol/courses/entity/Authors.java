package com.grammercetamol.courses.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String mail;

    public Authors(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
}
