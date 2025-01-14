package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.student.Gender;
import com.vulcan.dev_test.domain.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByFirstName(String firstName, Pageable pageable);
    @Query("SELECT COUNT(s) FROM Student s JOIN s.courses c WHERE s.gender = :gender AND c = :course")
    Long countStudentsByGenderAndCourse(@Param("gender") Gender gender, @Param("course") Course course);
    @Query("SELECT s.gender, COUNT(s) FROM Student s JOIN s.courses c WHERE c.id = :courseId GROUP BY s.gender")
    List<Object[]> countStudentsByGender(@Param("courseId") Long courseId);
    @Query("SELECT COUNT(s) FROM Student s JOIN s.courses c WHERE c = :course")
    Long countByCourse(@Param("course") Course course);
}
