package com.example.check.controller;

import com.example.check.payload.response.AttendanceCountResponse;
import com.example.check.payload.response.AttendanceResponse;
import com.example.check.payload.response.StudentGraphResponse;
import com.example.check.payload.response.StudentResponse;
import com.example.check.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public void postAttendance() {
        attendanceService.createAttendance();
    }

    @GetMapping("/{date}")
    public List<AttendanceResponse> getTodayAttendance(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                                           @PathVariable LocalDate date){
        return attendanceService.getTodayAttendanceList(date);
    }

    @GetMapping
    public List<AttendanceCountResponse> getAttendanceList() {
        return attendanceService.getAttendanceList();
    }

    @GetMapping("/profile/{studentId}")
    public List<AttendanceResponse> getStudentAttendanceList(@PathVariable String studentId) {
        return attendanceService.getStudentAttendanceList(studentId);
    }

    @GetMapping("/attendancestatus")
    public StudentGraphResponse getNotAttendanceStudent() {
        return attendanceService.getNotAttendanceStudent();
    }

}
