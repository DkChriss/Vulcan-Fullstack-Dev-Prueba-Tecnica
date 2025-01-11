package com.vulcan.dev_test.domain.student.service;

import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.repository.StudentDao;
import com.vulcan.dev_test.handler.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    public Page<Student> list(String name, PageRequest pageRequest) {
        return this.studentDao.list(name, pageRequest);
    }

    public Student store(Student student) {
        return this.studentDao.store(student);
    }

    public Student show(BigInteger id) {
        return this.studentDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno que desea obtener"));
    }

    public Student update(Student student) {
        return this.studentDao.update(student);
    }

    public void destroy(BigInteger id) {
        Student student = this.studentDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno que desea eliminar"));
        this.studentDao.destroy(student);
    }
}
