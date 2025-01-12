package com.vulcan.dev_test.domain.course.rest.request;

public record CourseDto(
        Long id,
        String name,
        Boolean status,
        Integer places,
        Integer occupiedPlaces
) {
}
