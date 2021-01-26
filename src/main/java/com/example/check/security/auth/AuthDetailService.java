package com.example.check.security.auth;

import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public AuthDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findById(username)
                .map(AuthDetails::new)
                .orElseThrow(StudentNotFoundException::new);
    }

}
