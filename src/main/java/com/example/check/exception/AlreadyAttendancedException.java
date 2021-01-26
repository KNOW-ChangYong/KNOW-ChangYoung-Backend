package com.example.check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already Attendanced Today")
public class AlreadyAttendancedException extends RuntimeException{
}
