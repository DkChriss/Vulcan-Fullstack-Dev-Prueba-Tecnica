package com.vulcan.dev_test.domain.course.repository;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.rest.request.CourseArrayResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseStatisticsResponse;
import com.vulcan.dev_test.domain.course.rest.request.CourseStats;
import com.vulcan.dev_test.domain.student.Gender;
import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository("course-jpa")
@RequiredArgsConstructor
public class CourseJpaDao implements CourseDao {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public Integer size() {
        return this.courseRepository.findAll().size();
    }

    @Override
    @Transactional
    public Page<Course> list(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        if(name == null || name.isEmpty()) {
            return this.courseRepository.findAll(pageable);
        }
        return this.courseRepository.findByName(name, pageable);
    }

    @Override
    @Transactional
    public Course store(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    @Transactional
    public Optional<Course> show(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return this.courseRepository.save(course);
    }

    @Override
    @Transactional
    public void destroy(Course course) {
        this.courseRepository.delete(course);
    }

    @Override
    @Transactional
    public Course assignStudents(Course course, List<Long> students) {
        Set<Student> studentSet = new HashSet<>(this.studentRepository.findAllById(students));
        course.getStudents().addAll(studentSet);
        for (Student student : studentSet) {
            if (course.getOccupiedPlaces() < course.getPlaces()) {
                course.getStudents().add(student);
                student.getCourses().add(course);
                course.setOccupiedPlaces(course.getOccupiedPlaces() + 1);

                if (course.getOccupiedPlaces() == course.getPlaces()) {
                    course.setStatus(false);
                }

            } else {
                System.out.println("El curso " + course.getName() + " está lleno. No se puede asignar al estudiante " + student.getFirstName());
            }
        }
        return this.courseRepository.save(course);
    }

    @Override
    @Transactional
    public CourseStatisticsResponse getGeneral() {
        CourseStatisticsResponse response = new CourseStatisticsResponse();

        Long totalCourses = this.courseRepository.count();
        response.setTotalCourses(totalCourses);

        Long totalStudents = this.studentRepository.count();
        response.setTotalStudents(totalStudents);

        List<CourseStats> coursesStats = calculateCoursesStatistics();
        response.setCourses(coursesStats);

        return response;
    }

    private List<CourseStats> calculateCoursesStatistics() {
        List<CourseStats> result = new ArrayList<>();

        List<Course> courses = courseRepository.findAll();

        for (Course course : courses) {
            CourseStats stats = new CourseStats();
            stats.setName(course.getName());

            Double averageMale = calculateAverageByGender(course, Gender.Masculino);
            stats.setAverageGenderMale(averageMale);

            Double averageFemale = calculateAverageByGender(course, Gender.Femenino);
            stats.setAverageGenderFemale(averageFemale);

            Double averageCapacity = calculateAverageCapacity(course);
            stats.setAverageCapacity(averageCapacity);

            result.add(stats);
        }

        return result;
    }

    private Double calculateAverageByGender(Course course, Gender gender) {
        Long totalStudents = studentRepository.countByCourse(course);
        Long count = studentRepository.countStudentsByGenderAndCourse(gender, course);
        return totalStudents > 0 ? (double) count / totalStudents * 100 : 0.0;
    }

    private Double calculateAverageCapacity(Course course) {
        double totalPlaces = course.getPlaces();
        double occupiedPlaces = course.getOccupiedPlaces();
        return totalPlaces > 0 ? (occupiedPlaces / totalPlaces) * 100 : 0.0;
    }

    @Override
    @Transactional
    public CourseArrayResponse listWithStudents() {
        List<Course> courses = this.courseRepository.findAll();
        CourseArrayResponse response = new CourseArrayResponse();
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courses) {
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setName(course.getName());
            courseResponse.setOccupiedPlaces(course.getOccupiedPlaces());
            courseResponse.setCountGender(this.getStudentCountByGender(course.getId()));
            courseResponse.setAverageCapacity(
                    ((double)(course.getPlaces() - course.getOccupiedPlaces()) / course.getPlaces()) * 100
            );
            courseResponseList.add(courseResponse);
        }
        response.setCourses(courseResponseList);
        return response;
    }

    private Map<String, Long> getStudentCountByGender(Long courseId) {
        List<Object[]> results = this.studentRepository.countStudentsByGender(courseId);
        Map<String, Long> genderCountMap = new HashMap<>();

        for (Object[] result : results) {
            String gender = result[0].toString();
            Long count = (Long) result[1];
            genderCountMap.put(gender, count);
        }

        return genderCountMap;
    }

    @Override
    @Transactional
    public List<Course> getCourses() {
        return this.courseRepository.findAllByStatus(true);
    }
}
