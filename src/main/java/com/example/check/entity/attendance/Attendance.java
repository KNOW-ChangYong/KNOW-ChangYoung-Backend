package com.example.check.entity.attendance;

import com.example.check.entity.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "attendance_tbl")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "student")
    private Student student;

}
