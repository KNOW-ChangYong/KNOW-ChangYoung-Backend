package com.example.check.entity.attendance;

import com.example.check.entity.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    Page<Attendance> findAllByStudent(Student student);

    List<Attendance> findAllByIpAddressAndDateTimeBetween(String ipAddress, LocalDateTime startDate, LocalDateTime endDate);

    Attendance findByStudentAndAndDateTimeBetween(Student student, LocalDateTime startDate, LocalDateTime endDate);

}
