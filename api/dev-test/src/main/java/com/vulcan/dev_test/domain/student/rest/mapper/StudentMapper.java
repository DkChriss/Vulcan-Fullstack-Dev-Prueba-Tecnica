package com.vulcan.dev_test.domain.student.rest.mapper;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.request.StudentDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentList;
import com.vulcan.dev_test.domain.student.rest.request.StudentStoreDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    //STORE
    public Student toEntity(StudentStoreDto studentStoreDto) {
        if(studentStoreDto == null) {
            return null;
        }
        return Student.builder()
                .firstName(studentStoreDto.firstName())
                .lastName(studentStoreDto.lastName())
                .age(studentStoreDto.age())
                .gender(studentStoreDto.gender())
                .build();
    }
    //RESPONSE
    public StudentDto toDto(Student student) {
        if(student == null) {
            return null;
        }
        if(student.getCourses() == null || student.getCourses().isEmpty()) {
            return new StudentDto(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getAge(),
                    student.getGender(),
                    null
            );
        }
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                student.getGender(),
                student.getCourses().stream().map(
                        Course::getName
                ).toList()
        );
    }
    //UPDATE
    public Student toEntity(@Valid StudentUpdateDto studentUpdateDto) {
        if(studentUpdateDto == null) {
            return null;
        }
        return Student.builder()
                .id(studentUpdateDto.getId())
                .firstName(studentUpdateDto.getFirstName())
                .lastName(studentUpdateDto.getLastName())
                .age(studentUpdateDto.getAge())
                .gender(studentUpdateDto.getGender())
                .build();
    }
    //LIST
    public StudentList toListDto(Student student) {
        if(student == null) {
            return null;
        }
        return new StudentList(
                student.getId(),
                student.getFirstName(),
                student.getLastName()
        );
    }
}
