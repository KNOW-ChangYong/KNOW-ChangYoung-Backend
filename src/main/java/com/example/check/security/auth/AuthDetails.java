package com.example.check.security.auth;

import com.example.check.entity.attendance.AttendanceRepository;
import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class AuthDetails implements UserDetails {

    private Student student;
    private AttendanceRepository attendanceRepository;

    public boolean isAttendanced() {
        LocalDate now = LocalDate.now();
        return !attendanceRepository.
                findByStudentAndAndDateTimeBetween(student,
                        LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),5,59),
                        LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),8,02))
                .equals(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return student.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
