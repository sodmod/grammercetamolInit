package com.grammercetamol.controller_advice;

import com.grammercetamol.exceptions.CourseUploadException;
import com.grammercetamol.exceptions.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorMessage refreshTokenNotFound(TokenRefreshException ex, WebRequest request) {
        return new ErrorMessage(
                FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(value = CourseUploadException.class)
    @ResponseStatus(NO_CONTENT)
    public CloudinaryErrorMessage cloudinaryErrorMessage(CourseUploadException ex, WebRequest request) {
        return new CloudinaryErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage(),
                new Date()
        );
    }
}
