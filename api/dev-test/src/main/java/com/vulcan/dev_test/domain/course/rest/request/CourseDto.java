package com.vulcan.dev_test.domain.course.rest.request;

import jakarta.annotation.Nullable;

import java.util.List;

public record CourseDto(
        Long id,
        String name,
        Boolean status,
        Integer places,
        Integer occupiedPlaces,
        @Nullable List<String> students
) {
}
