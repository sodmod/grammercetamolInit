package com.grammercetamol.payload.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignUpResponse extends Response {

    public SignUpResponse(String message1, int messageCode1) {
        super(message1, messageCode1);
    }
}
