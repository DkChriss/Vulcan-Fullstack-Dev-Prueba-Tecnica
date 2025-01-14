package com.vulcan.dev_test.domain.course.service;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.repository.CourseDao;
import com.vulcan.dev_test.domain.course.rest.request.CourseArrayResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseStatisticsResponse;
import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.handler.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDao courseDao;

    public Integer size() {
        return this.courseDao.size();
    }

    public Page<Course> list(String name, int page, int size) {
        return this.courseDao.list(name, page, size);
    }

    public Course store(Course course) {
        return this.courseDao.store(course);
    }

    public Course show(Long id) {
        return this.courseDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el curso que desea obtener"));
    }

    public Course update(Course course) {
        Course currentCourse = this.courseDao.show(course.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el curso que desea actualizar"));
        course.setStudents(currentCourse.getStudents());
        return this.courseDao.update(course);
    }

    public void destroy(Long id) {
        Course course = this.courseDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el curso que desea eliminar"));
        this.courseDao.destroy(course);
    }

    public Course assignStudents(Long id, List<Long> students) {
        Course course = this.courseDao.show(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el curso que desea asignar los alumnos"));
        return this.courseDao.assignStudents(course, students);
    }

    public CourseStatisticsResponse generalResponse() {
        return this.courseDao.getGeneral();
    }

    public CourseArrayResponse getArrayResponse() {
        return this.courseDao.listWithStudents();
    }

    public List<Course> getCourses() {
        return this.courseDao.getCourses();
    }

}
