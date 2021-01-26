package com.example.check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason = "Invalid Token Exception")
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("INVALID_TOKEN");
    }
}
