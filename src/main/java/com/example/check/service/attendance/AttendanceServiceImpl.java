package com.example.check.service.attendance;

import com.example.check.entity.attendance.Attendance;
import com.example.check.entity.attendance.AttendanceRepository;
import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.exception.AlreadyAttendanceException;
import com.example.check.exception.AttendanceTimeException;
import com.example.check.exception.StudentNotFoundException;
import com.example.check.exception.UnAuthorizationException;
import com.example.check.payload.response.AttendanceCountResponse;
import com.example.check.payload.response.AttendanceResponse;
import com.example.check.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{

    private final AuthenticationFacade authenticationFacade;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startTime = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 5,30);
    LocalDateTime endTime = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), 8,2);

    @Override
    public void createAttendance() {

        if(!authenticationFacade.isLogined()) {
            throw new UnAuthorizationException();
        }

        Student student = studentRepository.findById(authenticationFacade.getStudentId())
                .orElseThrow(StudentNotFoundException::new);

        if(now.isBefore(startTime) || now.isAfter(endTime) ||
                (now.getDayOfWeek().getValue() == 6 || now.getDayOfWeek().getValue() == 7)) {
            throw new AttendanceTimeException();
        }
        if(!attendanceRepository.findAllByStudentAndDateTimeBetween(student,
                LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),0,0)
                , LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),23,59)).isEmpty()) {
            throw new AlreadyAttendanceException();
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

        List<Attendance> attendanceList = attendanceRepository.findAllByDateTimeBetweenOrderByDateTimeDesc(
                LocalDateTime.of(date, LocalTime.of(0,0)),
                LocalDateTime.of(date, LocalTime.of(23,59)));

        List<AttendanceResponse> attendanceResponses = new ArrayList<>();

        for(Attendance attendance : attendanceList) {
            attendanceResponses.add(
                    AttendanceResponse.builder()
                            .dateTime(attendance.getDateTime())
                            .id(attendance.getId())
                            .userId(attendance.getStudent().getId())
                            .name(attendance.getStudent().getName())
                            .build()
            );
        }

        return attendanceResponses;
    }

    @Override
    public List<AttendanceCountResponse> getAttendanceList() {

        List<Student> students = studentRepository.findAllByOrderByNameAsc();

        List<AttendanceCountResponse> attendanceCountResponses = new ArrayList<>();

        LocalDate startDate = LocalDate.of(2021,01,18);
        LocalDate todayDate = LocalDate.now();
        Integer dateSum = todayDate.getDayOfYear() - startDate.getDayOfYear() + 1;

        while(!startDate.isAfter(todayDate)) {
            if(startDate.getDayOfWeek().getValue() >= 6) {
                dateSum --;
            }
            startDate = startDate.plusDays(1);
        }

        for(Student student : students) {
            Integer count = attendanceRepository.countAllByStudent(student);
            attendanceCountResponses.add(
                AttendanceCountResponse.builder()
                        .notAttendanceCount(dateSum - count)
                        .attendanceCount(count)
                        .dateSum(dateSum)
                        .name(student.getName())
                        .build()
            );
        }

        return attendanceCountResponses;

    }

    @Override
    public List<AttendanceResponse> getStudentAttendanceList(String studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        List<Attendance> attendanceList = attendanceRepository.findAllByStudentOrderByDateTimeDesc(student);

        List<AttendanceResponse> attendanceResponses = new ArrayList<>();

        for(Attendance attendance : attendanceList) {
            attendanceResponses.add(
                    AttendanceResponse.builder()
                            .dateTime(attendance.getDateTime())
                            .id(attendance.getId())
                            .userId(attendance.getStudent().getId())
                            .name(attendance.getStudent().getName())
                            .build()
            );
        }

        return attendanceResponses;
    }
}
