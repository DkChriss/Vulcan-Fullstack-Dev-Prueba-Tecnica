package com.vulcan.dev_test.domain.student.rest;

import com.vulcan.dev_test.domain.student.Student;
import com.vulcan.dev_test.domain.student.rest.mapper.StudentMapper;
import com.vulcan.dev_test.domain.student.rest.request.StudentDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentStoreDto;
import com.vulcan.dev_test.domain.student.rest.request.StudentUpdateDto;
import com.vulcan.dev_test.domain.student.service.StudentService;
import com.vulcan.dev_test.handler.response.GlobalResponseEntity;
import com.vulcan.dev_test.handler.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
@Validated
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<StudentDto>>> list (
            @RequestParam(required = false, defaultValue = "", value = "name") String name,
            @PageableDefault Pageable pageable
    ) {
        Integer size = studentService.size();
       List<StudentDto> studentDtoList = studentService.list(
               name == null ? "" : name,
               pageable.getPageNumber(),
               pageable.getPageSize())
               .getContent()
               .stream()
               .map(studentMapper::toDto)
               .toList();
       Page<StudentDto> response = new PageImpl<>(studentDtoList, pageable, size);
       return GlobalResponseEntity.successResponse(
               "Se ha obtenido la lista de alumnos correctamente",
               "1",
               response,
               HttpStatus.OK
       );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<StudentDto>> store(
            @RequestBody @Valid StudentStoreDto studentStoreDto
    ) {
        Student student = studentMapper.toEntity(studentStoreDto);
        StudentDto response = studentMapper.toDto(studentService.store(student));

        return GlobalResponseEntity.successResponse(
                "Se ha registrado el alumno correctamente",
                "1",
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<StudentDto>> show(@PathVariable("id") Long id) {
        StudentDto response = studentMapper.toDto(studentService.show(id));
        return GlobalResponseEntity.successResponse(
                "Se ha obtenido el alumno correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessResponse<StudentDto>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid StudentUpdateDto studentUpdateDto
    ) {
        studentUpdateDto.setId(id);
        Student student = studentMapper.toEntity(studentUpdateDto);
        StudentDto response = studentMapper.toDto(studentService.update(student));

        return GlobalResponseEntity.successResponse(
                "Se ha actualizado los datos del alumno correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<StudentDto>> destroy(@PathVariable("id") Long id) {
        this.studentService.destroy(id);
        return GlobalResponseEntity.successResponse(
                "Se ha eliminado el alumno correctamente",
                "1",
                null,
                HttpStatus.OK
        );
    }
}
