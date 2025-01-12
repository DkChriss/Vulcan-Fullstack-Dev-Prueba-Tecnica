package com.vulcan.dev_test.domain.student.rest.request;

import com.vulcan.dev_test.domain.student.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentStoreDto(
        @NotEmpty(message = "El nombre del alumno es necesario")
        @NotBlank(message = "El nombre del alumno es necesario")
        @NotNull(message = "El nombre del alumno es necesario")
        String firstName,
        @NotEmpty(message = "El apellido del alumno es necesario")
        @NotBlank(message = "El apellido del alumno es necesario")
        @NotNull(message = "El apelliudo del alumno es necesario")
        String lastName,
        @NotNull(message = "La edad del alumno es necesario")
        Integer age,
        Gender gender
) {
}
