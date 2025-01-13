package com.vulcan.dev_test.domain.auth.service;

import com.vulcan.dev_test.domain.auth.jwt.JwtService;
import com.vulcan.dev_test.domain.auth.rest.request.AuthRequest;
import com.vulcan.dev_test.domain.auth.rest.request.AuthStoreRequest;
import com.vulcan.dev_test.domain.auth.rest.response.AuthResponse;
import com.vulcan.dev_test.domain.user.User;
import com.vulcan.dev_test.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthStoreRequest authRequest) {
        User user = User.builder()
                .name(authRequest.getName())
                .username(authRequest.getUsername())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .build();
        this.userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
