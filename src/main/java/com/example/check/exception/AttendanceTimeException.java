package com.example.check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Not Time To Attendance")
public class AttendanceTimeException extends RuntimeException{
}
