package com.vulcan.dev_test.domain.course.repository;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.rest.request.CourseArrayResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseStatisticsResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    Integer size();
    Page<Course> list(String name, int page, int size);
    Course store(Course course);
    Optional<Course> show(Long id);
    Course update(Course course);
    void destroy(Course course);
    Course assignStudents(Course course, List<Long> students);
    CourseStatisticsResponse getGeneral();
    CourseArrayResponse listWithStudents();
    List<Course> getCourses();
}
