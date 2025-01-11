package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository("student-jpa")
@RequiredArgsConstructor
public class StudentJpaDao implements StudentDao {

    private final StudentRepository studentRepository;

    @Override
    public Page<Student> list(String name, PageRequest pageRequest) {
        return null;
    }

    @Override
    @Transactional
    public Student store(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    @Transactional
    public Optional<Student> show(BigInteger id) {
        return this.studentRepository.findById(id);
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    @Transactional
    public void destroy(Student student) {
        this.studentRepository.delete(student);
    }
}
