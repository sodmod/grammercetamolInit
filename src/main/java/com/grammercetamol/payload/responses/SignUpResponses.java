package com.grammercetamol.payload.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignUpResponses extends Responses {

    public SignUpResponses(String message1, int messageCode1) {
        super(message1, messageCode1);
    }
}
