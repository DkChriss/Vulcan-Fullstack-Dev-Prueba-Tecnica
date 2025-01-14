package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.request.HomeResponse;
import com.vulcan.dev_test.domain.student.rest.request.StudentList;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Integer size();
    Page<Student> list(String name, int page, int size);
    Student store(Student student);
    Optional<Student> show(Long id);
    Student update(Student student);
    void destroy(Student student);
    Student assignCourses(Student student, List<Long> courses);
    HomeResponse home();
    List<Student> getStudents();
}
