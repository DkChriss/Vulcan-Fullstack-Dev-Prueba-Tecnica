package com.vulcan.dev_test.domain.student.rest.request;

import com.vulcan.dev_test.domain.student.Gender;

import java.math.BigInteger;

public record StudentDto(
        BigInteger id,
        String firstName,
        String lastName,
        Integer age,
        Gender gender
) {
}
