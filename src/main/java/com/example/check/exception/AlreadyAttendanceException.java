package com.example.check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS, reason = "Already Attendanced Today")
public class AlreadyAttendanceException extends RuntimeException{
}
