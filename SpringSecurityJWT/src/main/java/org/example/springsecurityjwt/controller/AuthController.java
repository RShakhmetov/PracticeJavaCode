package org.example.springsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.DTO.*;
import org.example.springsecurityjwt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> signUp(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.signUp(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest registerRequest) {
        return ResponseEntity.ok(authService.signIn(registerRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshToken> refresh(@RequestBody RefreshToken registerRequest) {
        return ResponseEntity.ok(authService.refreshToken(registerRequest));
    }
}
