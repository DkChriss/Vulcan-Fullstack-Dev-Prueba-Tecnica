package com.vulcan.dev_test.domain.course.rest.mapper;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.rest.request.CourseDto;
import com.vulcan.dev_test.domain.course.rest.request.CourseStoreDto;
import com.vulcan.dev_test.domain.course.rest.request.CourseUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    //STORE
    public Course toEntity(CourseStoreDto courseStoreDto) {
        if (courseStoreDto == null) {
            return null;
        }
        return Course.builder()
                .name(courseStoreDto.name())
                .status(courseStoreDto.status())
                .places(courseStoreDto.places())
                .occupiedPlaces(0)
                .build();
    }
    //RESPONSE
    public CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getStatus(),
                course.getPlaces(),
                course.getOccupiedPlaces()
        );
    }
    //UPDATE
    public Course toEntity(@Valid CourseUpdateDto courseUpdateDto) {
        if (courseUpdateDto == null) {
            return null;
        }
        return Course.builder()
                .id(courseUpdateDto.getId())
                .name(courseUpdateDto.getName())
                .status(courseUpdateDto.getStatus())
                .places(courseUpdateDto.getPlaces())
                .occupiedPlaces(courseUpdateDto.getOccupiedPlaces())
                .build();
    }
}
