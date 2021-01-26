package com.example.check.service.auth;

import com.example.check.entity.refreshtoken.RefreshToken;
import com.example.check.entity.refreshtoken.RefreshTokenRepository;
import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.InvalidTokenException;
import com.example.check.exception.UserNotFoundException;
import com.example.check.payload.AuthRequest;
import com.example.check.payload.AuthResponse;
import com.example.check.payload.RefreshTokenResponse;
import com.example.check.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

    @Value("${auth.jwt.exp.access}")
    private Long accessExp;

    @Override
    public AuthResponse signIn(AuthRequest authRequest) {

        Student student = studentRepository.findById(authRequest.getId())
                .orElseThrow(UserNotFoundException::new);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authRequest.getId());

        RefreshToken token = new RefreshToken(authRequest.getId(),refreshToken,refreshExp);
        refreshTokenRepository.save(token);

        return new AuthResponse(
                jwtTokenProvider.generateAccessToken(authRequest.getId())
                , token.getRefreshToken(), accessExp);

    }

    @Override
    public RefreshTokenResponse tokenRefresh(String token) {
        if(!jwtTokenProvider.validateToken(token)) {
            throw new InvalidTokenException();
        }

        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(InvalidTokenException::new);

        refreshToken.update(refreshExp);

        return new RefreshTokenResponse(jwtTokenProvider.generateAccessToken(refreshToken.getId()));

    }
}
