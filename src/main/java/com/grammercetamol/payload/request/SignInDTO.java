package com.grammercetamol.payload.request;

import lombok.Data;

@Data
public class SignInDTO {
    private String username;
    private String password;
}
