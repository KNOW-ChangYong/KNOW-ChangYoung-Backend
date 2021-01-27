package com.example.check.service.attendance;

import com.example.check.payload.request.AttendanceRequest;
import com.example.check.payload.response.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    void createAttendance(AttendanceRequest request);

    List<AttendanceResponse> getTodayAttendanceList(LocalDate date);

    List<AttendanceResponse> getAttendanceList();

    List<AttendanceResponse> getStudentAttendanceList(String studentId);

}
