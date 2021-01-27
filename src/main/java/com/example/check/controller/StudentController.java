package com.example.check.controller;

import com.example.check.entity.student.StudentRepository;
import com.example.check.payload.response.StudentResponse;
import com.example.check.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public StudentResponse getStudent() {
        return studentService.getStudent();
    }
}
