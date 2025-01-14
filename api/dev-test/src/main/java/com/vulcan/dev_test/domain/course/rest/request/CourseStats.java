package com.vulcan.dev_test.domain.course.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStats {
    private String name;
    private Double averageGenderMale;
    private Double averageGenderFemale;
    private Double averageCapacity;
}
