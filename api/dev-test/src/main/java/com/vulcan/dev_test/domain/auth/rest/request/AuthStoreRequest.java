package com.vulcan.dev_test.domain.auth.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthStoreRequest {
    String name;
    String username;
    String password;
}
