package com.vulcan.dev_test.domain.course.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private String name;
    private Integer occupiedPlaces;
    private Map<String, Long> countGender;
    private Double averageCapacity;
}
