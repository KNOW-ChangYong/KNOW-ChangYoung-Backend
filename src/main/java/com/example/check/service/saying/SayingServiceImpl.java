package com.example.check.service.saying;

import com.example.check.entity.saying.Saying;
import com.example.check.entity.saying.SayingRepository;
import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.StudentNotFoundException;
import com.example.check.exception.UnAuthorizationException;
import com.example.check.exception.UserNotFoundException;
import com.example.check.payload.response.SayingResponse;
import com.example.check.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SayingServiceImpl implements SayingService{

    private final AuthenticationFacade authenticationFacade;
    private final StudentRepository studentRepository;
    private final SayingRepository sayingRepository;

    @Override
    public void createSaying(String content) {
        if(!authenticationFacade.isLogined()) {
            throw new UnAuthorizationException();
        }

        Student student = studentRepository.findById(authenticationFacade.getStudentId())
                .orElseThrow(StudentNotFoundException::new);

        sayingRepository.save(
                Saying.builder()
                        .content(content)
                        .studentId(student.getId())
                        .build()
        );

    }

    @Override
    public SayingResponse getSaying() {
        Saying saying = sayingRepository.findRandomSaying()
                .orElseThrow(StudentNotFoundException::new);

        return SayingResponse.builder()
                .content(saying.getContent())
                .studentId(saying.getStudentId())
                .build();

    }
}
