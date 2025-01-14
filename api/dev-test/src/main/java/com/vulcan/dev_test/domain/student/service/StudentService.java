package com.vulcan.dev_test.domain.student.service;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.repository.CourseRepository;
import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.repository.StudentDao;
import com.vulcan.dev_test.domain.student.rest.request.HomeResponse;
import com.vulcan.dev_test.domain.student.rest.request.StudentList;
import com.vulcan.dev_test.handler.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;
    private final CourseRepository courseRepository;

    public Integer size() {
        return this.studentDao.size();
    }

    public Page<Student> list(String name, int page, int size) {
        return this.studentDao.list(name, page, size);
    }

    public Student store(Student student) {
        return this.studentDao.store(student);
    }

    public Student show(Long id) {
        return this.studentDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno que desea obtener"));
    }

    public Student update(Student student) {
        return this.studentDao.update(student);
    }

    public void destroy(Long id) {
        Student student = this.studentDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno que desea eliminar"));
        this.studentDao.destroy(student);
    }

    public Student assignCourses(Long id, List<Long> courses) {
        Student student = this.studentDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno que desea asignar los cursos"));
        return this.studentDao.assignCourses(student, courses);
    }

    public HomeResponse home() {
        return this.studentDao.home();
    }

    public List<Student> getStudents() {
        return this.studentDao.getStudents();
    }
}
