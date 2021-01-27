package com.example.check.service.auth;

import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.UnAuthorizationException;
import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.TokenResponse;
import com.example.check.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.exp.access}")
    private Long accessExp;

    @Override
    public TokenResponse signIn(AuthRequest authRequest) {
        Student student = studentRepository.findById(authRequest.getId())
                .orElseThrow(UnAuthorizationException::new);

        student.update(authRequest.getNickname());

        studentRepository.save(student);

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(authRequest.getId()))
                .tokenType("access")
                .accessTokenExp(accessExp)
                .build();
    }

}
