package com.example.check.controller;

import com.example.check.entity.attendance.Attendance;
import com.example.check.payload.request.AttendanceRequest;
import com.example.check.payload.response.AttendanceResponse;
import com.example.check.service.attendance.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public void postAttendance(AttendanceRequest request) {
        attendanceService.createAttendance(request);
    }

    @GetMapping("/{date}")
    public List<AttendanceResponse> getTodayAttendance(@PathVariable LocalDate date){
        return attendanceService.getTodayAttendanceList(date);
    }

}
