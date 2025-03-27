package org.example.springsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.DTO.ReqRes;
import org.example.springsecurityjwt.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes registerRequest) {
        return ResponseEntity.ok(authService.signUp(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes registerRequest) {
        return ResponseEntity.ok(authService.signIn(registerRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes registerRequest) {
        return ResponseEntity.ok(authService.refreshToken(registerRequest));
    }
}
