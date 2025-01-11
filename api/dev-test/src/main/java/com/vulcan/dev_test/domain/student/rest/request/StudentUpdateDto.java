package com.vulcan.dev_test.domain.student.rest.request;

import com.vulcan.dev_test.domain.student.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDto {
    Long id;
    @NotEmpty(message = "El nombre del alumno es necesario")
    @NotBlank(message = "El nombre del alumno es necesario")
    @NotNull(message = "El nombre del alumno es necesario")
    String firstName;
    @NotEmpty(message = "El apellido del alumno es necesario")
    @NotBlank(message = "El apellido del alumno es necesario")
    @NotNull(message = "El apelliudo del alumno es necesario")
    String lastName;
    @NotNull(message = "La edad del alumno es necesario")
    Integer age;
    Gender gender;
}
