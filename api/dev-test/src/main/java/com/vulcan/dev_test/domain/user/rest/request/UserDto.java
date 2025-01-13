package com.vulcan.dev_test.domain.user.rest.request;

public record UserDto(
        Long id,
        String name,
        String username
) {
}
