package com.grammercetamol.payload.request;

import lombok.Data;

@Data
public class SignUp {
    private String firstname;
    private String lastname;
    private String othername;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
}
