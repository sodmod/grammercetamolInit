package com.grammercetamol.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(
        NOT_FOUND
)
public class CourseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CourseException(String message) {
        super(
                String.format(
                        "%s",
                        message
                )
        );
    }
}
