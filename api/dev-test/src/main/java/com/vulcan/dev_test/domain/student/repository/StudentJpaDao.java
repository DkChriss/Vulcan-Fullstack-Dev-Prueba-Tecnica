package com.vulcan.dev_test.domain.student.repository;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.repository.CourseRepository;
import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.request.HomeResponse;
import com.vulcan.dev_test.domain.student.rest.request.StudentList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("student-jpa")
@RequiredArgsConstructor
public class StudentJpaDao implements StudentDao {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

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

    @Override
    @Transactional
    public Student assignCourses(Student student, List<Long> courses) {
        Set<Course> courseSet = new HashSet<>(this.courseRepository.findAllById(courses));
        student.getCourses().addAll(courseSet);
        for (Course course : courseSet) {
            if (course.getOccupiedPlaces() < course.getPlaces()) {
                course.getStudents().add(student);
                course.setOccupiedPlaces(course.getOccupiedPlaces() + 1);
                if (course.getOccupiedPlaces() == course.getPlaces()) {
                    course.setStatus(false);
                }
                this.courseRepository.save(course);
            } else {
                System.out.println("El curso " + course.getName() + " está lleno.");
            }
        }
        return this.studentRepository.save(student);
    }

    @Override
    @Transactional
    public HomeResponse home() {
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setTotalCourses(this.studentRepository.count());
        homeResponse.setTotalCompleteCourses(this.courseRepository.countByStatusFalse());
        homeResponse.setTotalStudents(this.studentRepository.count());
        return homeResponse;
    }

    @Override
    @Transactional
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }
}
