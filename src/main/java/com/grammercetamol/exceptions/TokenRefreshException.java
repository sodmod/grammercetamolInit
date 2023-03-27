package com.grammercetamol.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(
        NOT_FOUND
)
public class TokenRefreshException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TokenRefreshException(String token, String message) {
        super(
                String.format(
                        "Token not Found",
                        token,
                        message
                )
        );
    }
}
