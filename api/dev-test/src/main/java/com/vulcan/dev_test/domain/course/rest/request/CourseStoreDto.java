package com.vulcan.dev_test.domain.course.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseStoreDto(
        @NotEmpty(message = "El nombre del curso es necesario")
        @NotBlank(message = "El nombre del curso es necesario")
        @NotNull(message = "El nombre del curso es necesario")
        String name,
        @NotNull(message = "El estado del curso es necesario")
        Boolean status,
        @NotNull(message = "El numero de cupos es necesario")
        Integer places
) {
}
