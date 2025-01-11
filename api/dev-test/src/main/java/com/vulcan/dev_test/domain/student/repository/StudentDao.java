package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.Optional;

public interface StudentDao {
    Page<Student> list(String name, PageRequest pageRequest);
    Student store(Student student);
    Optional<Student> show(BigInteger id);
    Student update(Student student);
    void destroy(Student student);
}
