package com.example.check.service.student;

import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.StudentNotFoundException;
import com.example.check.payload.response.StudentResponse;
import com.example.check.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public StudentResponse getStudent() {

        if(!authenticationFacade.isLogined()) {
            throw new StudentNotFoundException();
        }

        Student student = studentRepository.findById(authenticationFacade.getStudentId())
                .orElseThrow(StudentNotFoundException::new);

        return StudentResponse.builder()
                .name(student.getName())
                .nickname(student.getNickname())
                .userId(student.getId())
                .build();
    }
}
