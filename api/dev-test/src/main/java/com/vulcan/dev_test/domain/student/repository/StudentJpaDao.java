package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.student.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("student-jpa")
@RequiredArgsConstructor
public class StudentJpaDao implements StudentDao {

    private final StudentRepository studentRepository;

    @Override
    public Integer size() {
        return this.studentRepository.findAll().size();
    }

    @Override
    @Transactional
    public Page<Student> list(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        if(name == null || name.isEmpty()) {
            return studentRepository.findAll(pageable);
        }
        return studentRepository.findByFirstName(name,pageable);
    }

    @Override
    @Transactional
    public Student store(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    @Transactional
    public Optional<Student> show(Long id) {
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
