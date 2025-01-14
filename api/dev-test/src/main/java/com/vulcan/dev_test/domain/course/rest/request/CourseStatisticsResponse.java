package com.vulcan.dev_test.domain.course.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStatisticsResponse {
    private Long totalCourses;
    private Long totalStudents;
    private List<CourseStats> courses;
}
