package com.vulcan.dev_test.domain.student.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponse {
    private Long totalCourses;
    private Long totalCompleteCourses;
    private Long totalStudents;
}
