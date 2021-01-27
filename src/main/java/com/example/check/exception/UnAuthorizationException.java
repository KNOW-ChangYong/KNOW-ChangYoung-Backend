package com.example.check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Invalid Token Exception")
public class UnAuthorizationException extends RuntimeException {
}
