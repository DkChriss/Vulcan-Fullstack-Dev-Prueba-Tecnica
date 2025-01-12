package com.vulcan.dev_test.domain.student.rest.request;

import com.vulcan.dev_test.domain.student.Gender;

public record StudentDto(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender
) {
}
