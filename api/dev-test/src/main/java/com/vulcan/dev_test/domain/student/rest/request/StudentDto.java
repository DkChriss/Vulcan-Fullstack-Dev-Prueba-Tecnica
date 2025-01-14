package com.vulcan.dev_test.domain.student.rest.request;

import com.vulcan.dev_test.domain.student.Gender;
import jakarta.annotation.Nullable;

import java.util.List;

public record StudentDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender,
        @Nullable List<String> courses
) {
}
