package com.grammercetamol.payload.request;

import lombok.Data;

@Data
public class SignIn {
    private String email;
    private String password;
}
