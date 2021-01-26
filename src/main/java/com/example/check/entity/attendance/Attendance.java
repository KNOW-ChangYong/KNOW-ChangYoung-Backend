package com.example.check.entity.attendance;

import com.example.check.entity.student.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "attendance_tbl")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private String ipAddress;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "student")
    private Student student;

}
