package com.example.check.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentGraphResponse {

    private List<StudentResponse> studentResponses;

    private double lastDayGraph;

    private double todayGraph;

}
