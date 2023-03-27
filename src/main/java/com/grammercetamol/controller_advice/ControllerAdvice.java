package com.grammercetamol.controller_advice;

import com.grammercetamol.exceptions.TokenRefreshException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
}
