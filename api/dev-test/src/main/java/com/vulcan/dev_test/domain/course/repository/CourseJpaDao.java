package com.vulcan.dev_test.domain.course.repository;

import com.vulcan.dev_test.domain.course.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("course-jpa")
@RequiredArgsConstructor
public class CourseJpaDao implements CourseDao {

    private final CourseRepository courseRepository;

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
}
