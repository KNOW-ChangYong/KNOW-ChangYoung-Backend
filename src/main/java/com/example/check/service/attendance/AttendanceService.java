package com.example.check.service.attendance;

import com.example.check.payload.response.AttendanceCountResponse;
import com.example.check.payload.response.AttendanceResponse;
import com.example.check.payload.response.StudentGraphResponse;
import com.example.check.payload.response.StudentResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    void createAttendance();

    List<AttendanceResponse> getTodayAttendanceList(LocalDate date);

    List<AttendanceCountResponse> getAttendanceList();

    List<AttendanceResponse> getStudentAttendanceList(String studentId);

    StudentGraphResponse getNotAttendanceStudent();

}
