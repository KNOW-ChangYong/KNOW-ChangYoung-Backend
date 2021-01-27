package com.example.check.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCountResponse {

    private Integer attendanceCount;

    private Integer notAttendanceCount;

    private Integer dateSum;

    private String name;
}
