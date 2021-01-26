package com.example.check.entity.attendance;

import com.example.check.entity.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    List<Attendance> findAllByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
