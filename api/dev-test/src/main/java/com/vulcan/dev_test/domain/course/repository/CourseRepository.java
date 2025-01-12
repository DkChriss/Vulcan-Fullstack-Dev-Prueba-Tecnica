package com.vulcan.dev_test.domain.course.repository;

import com.vulcan.dev_test.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByName(String name, Pageable pageable);
}
