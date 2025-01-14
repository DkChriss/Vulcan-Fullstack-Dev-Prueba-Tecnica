package com.vulcan.dev_test.domain.auth.rest.response;

import com.vulcan.dev_test.domain.user.rest.request.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
    UserDto user;
}
