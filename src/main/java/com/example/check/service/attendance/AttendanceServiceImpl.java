package com.example.check.service.attendance;

import com.example.check.entity.attendance.Attendance;
import com.example.check.entity.attendance.AttendanceRepository;
import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.AlreadyAttendancedException;
import com.example.check.exception.StudentNotFoundException;
import com.example.check.exception.UnAuthorizationException;
import com.example.check.exception.UserNotFoundException;
import com.example.check.payload.request.AttendanceRequest;
import com.example.check.payload.response.AttendanceResponse;
import com.example.check.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{

    private final AuthenticationFacade authenticationFacade;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Override
    public void createAttendance(AttendanceRequest request) {

        System.out.println("서비스");
        System.out.println(authenticationFacade.isLogined());
        System.out.println(authenticationFacade.getStudentId());
        if(!authenticationFacade.isLogined()) {
            throw new UnAuthorizationException();
        }

        Student student = studentRepository.findById(authenticationFacade.getStudentId())
                .orElseThrow(StudentNotFoundException::new);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startTime = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 6,0);
        LocalDateTime endTime = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 8,2);

        if(/*now.isBefore(endTime) || now.isAfter(startTime) ||*/
                !attendanceRepository.findAllByStudentAndDateTimeBetween(student, startTime, endTime).isEmpty()) {
            throw new AlreadyAttendancedException();
        }

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null) { ip = req.getRemoteAddr(); }

        attendanceRepository.save(
                Attendance.builder()
                        .ipAddress(ip)
                        .dateTime(LocalDateTime.now())
                        .student(student)
                        .build()
        );

    }

    @Override
    public List<AttendanceResponse> getTodayAttendanceList(LocalDate date) {

        List<Attendance> attendanceList = attendanceRepository.findAllByDateTimeBetweenOrderByDateTime(
                LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(), 6,0,0),
                LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(), 8,2,0));

        List<AttendanceResponse> attendanceResponses = new ArrayList<>();

        for(Attendance attendance : attendanceList) {
            attendanceResponses.add(
                    AttendanceResponse.builder()
                            .dateTime(attendance.getDateTime())
                            .id(attendance.getId())
                            .userId(attendance.getStudent().getId())
                            .build()
            );
        }

        return attendanceResponses;
    }

    @Override
    public List<AttendanceResponse> getAttendanceList() {

        List<Attendance> attendanceList = attendanceRepository.findAllBy();

        List<AttendanceResponse> attendanceResponses = new ArrayList<>();

        for(Attendance attendance : attendanceList) {
            attendanceResponses.add(
                    AttendanceResponse.builder()
                            .dateTime(attendance.getDateTime())
                            .id(attendance.getId())
                            .userId(attendance.getStudent().getId())
                            .build()
            );
        }

        return attendanceResponses;
    }

    @Override
    public List<AttendanceResponse> getStudentAttendanceList(String studentId) {
        if(!authenticationFacade.isLogined()) {
            throw new UnAuthorizationException();
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        List<Attendance> attendanceList = attendanceRepository.findAllByStudent(student);

        List<AttendanceResponse> attendanceResponses = new ArrayList<>();

        for(Attendance attendance : attendanceList) {
            attendanceResponses.add(
                    AttendanceResponse.builder()
                            .dateTime(attendance.getDateTime())
                            .id(attendance.getId())
                            .userId(attendance.getStudent().getId())
                            .build()
            );
        }

        return attendanceResponses;
    }
}
