package com.vulcan.dev_test.domain.course.rest;

import com.vulcan.dev_test.domain.course.Course;
import com.vulcan.dev_test.domain.course.rest.mapper.CourseMapper;
import com.vulcan.dev_test.domain.course.rest.request.CourseDto;
import com.vulcan.dev_test.domain.course.rest.request.CourseStoreDto;
import com.vulcan.dev_test.domain.course.rest.request.CourseUpdateDto;
import com.vulcan.dev_test.domain.course.service.CourseService;
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
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
@Validated
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<CourseDto>>> list (
            @RequestParam(required = false, defaultValue = "", value = "name") final String name,
            @PageableDefault Pageable pageable
            ) {
        Integer size = this.courseService.size();

        List<CourseDto> courseDtoList = this.courseService.list(
                name == null ? "": name,
                pageable.getPageNumber(),
                pageable.getPageSize())
                .getContent()
                .stream()
                .map(courseMapper::toDto)
                .toList();
        Page<CourseDto> response = new PageImpl<>(courseDtoList, pageable, size);
        return GlobalResponseEntity.successResponse(
                "Se ha obtenido la lista de cursos correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<CourseDto>> store(
            @RequestBody @Valid CourseStoreDto courseStoreDto
    ) {
        Course course = this.courseMapper.toEntity(courseStoreDto);
        CourseDto response = this.courseMapper.toDto(this.courseService.store(course));

        return GlobalResponseEntity.successResponse(
                "Se ha registrado el curso correctamente",
                "1",
                response,
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<CourseDto>> show(
            @PathVariable("id") Long id
    ) {
        CourseDto response = this.courseMapper.toDto(this.courseService.show(id));
        return GlobalResponseEntity.successResponse(
                "Se ha obtenido el curso correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessResponse<CourseDto>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid CourseUpdateDto courseUpdateDto
    ) {
        courseUpdateDto.setId(id);
        Course course = this.courseMapper.toEntity(courseUpdateDto);
        CourseDto response = this.courseMapper.toDto(this.courseService.update(course));

        return GlobalResponseEntity.successResponse(
                "Se ha actualizado el curso correctamente",
                "1",
                response,
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<CourseDto>> delete(
            @PathVariable("id") Long id
    ){
        this.courseService.destroy(id);
        return GlobalResponseEntity.successResponse(
                "Se ha eliminado el curso correctamente",
                "1",
                null,
                HttpStatus.OK
        );
    }


}
