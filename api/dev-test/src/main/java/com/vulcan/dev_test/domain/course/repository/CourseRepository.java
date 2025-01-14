package com.vulcan.dev_test.domain.course.repository;

import com.vulcan.dev_test.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByName(String name, Pageable pageable);
    @Query("SELECT c.places, c.occupiedPlaces FROM Course c")
    List<Object[]> findCourseCapacityStats();
    long countByStatusFalse();
    List<Course> findAllByStatus(boolean status);
}
