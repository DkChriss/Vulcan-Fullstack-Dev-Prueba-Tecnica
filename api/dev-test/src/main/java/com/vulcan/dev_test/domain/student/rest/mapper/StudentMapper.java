package com.vulcan.dev_test.domain.student.rest.mapper;

import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.request.StudentDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    //STORE
    public Student toEntity(StudentDto studentDto) {
        if(studentDto == null) {
            return null;
        }
        return Student.builder()
                .firstName(studentDto.firstName())
                .lastName(studentDto.lastName())
                .age(studentDto.age())
                .gender(studentDto.gender())
                .build();
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
    //RESPONSE
    public StudentDto toDto(Student student) {
        if(student == null) {
            return null;
        }
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                student.getGender()
        );
    }
}
