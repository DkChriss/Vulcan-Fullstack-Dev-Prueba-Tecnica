package com.vulcan.dev_test.domain.auth.rest;

import com.vulcan.dev_test.domain.auth.rest.request.AuthRequest;
import com.vulcan.dev_test.domain.auth.rest.request.AuthStoreRequest;
import com.vulcan.dev_test.domain.auth.rest.response.AuthResponse;
import com.vulcan.dev_test.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthStoreRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
