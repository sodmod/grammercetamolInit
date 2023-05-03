package com.grammercetamol.controller_advice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CloudinaryErrorMessage {
    private int statusCode;
    private String message;
    private Date timestamp;

}
