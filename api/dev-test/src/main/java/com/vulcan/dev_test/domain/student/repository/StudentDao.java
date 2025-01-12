package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface StudentDao {
    Integer size();
    Page<Student> list(String name, int page, int size);
    Student store(Student student);
    Optional<Student> show(Long id);
    Student update(Student student);
    void destroy(Student student);
}
