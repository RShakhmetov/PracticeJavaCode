package org.example.springsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.DTO.*;
import org.example.springsecurityjwt.model.OurUser;
import org.example.springsecurityjwt.model.Roles;
import org.example.springsecurityjwt.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    public RegisterResponse signUp(RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();
        if (userRepository.existsByUsername(registerRequest.getName())) {
            throw new RuntimeException("Username already exists");
        }
        try {
            OurUser ourUser = new OurUser();
            ourUser.setUsername(registerRequest.getName());
            ourUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            ourUser.setRole(Roles.valueOf(registerRequest.getRole()));
            OurUser savedUser = userRepository.save(ourUser);
            if (savedUser != null && savedUser.getId() > 0) {
                response.setStatusCode(200);
                response.setMessage("User Successfully Registered!");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public SignInResponse signIn(SignInRequest registerRequest) {
        SignInResponse response = new SignInResponse();
        try {
            OurUser user = userRepository.findByUsername(registerRequest.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getName(), registerRequest.getPassword()));

            OurUser foundedUser = userRepository.findByUsername(registerRequest.getName()).orElseThrow();

            if (!user.getIsAccountNonLocked()) {
                throw new RuntimeException("Account is locked due to too many failed login attempts");
            }

            System.out.println("USER IS " + foundedUser);
            String jwtToken = jwtUtils.generateToken(foundedUser);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), foundedUser);
            foundedUser.setFailedLoginAttempts(0);
            response.setStatusCode(200);
            response.setToken(jwtToken);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("User Successfully Signed in!");
            userRepository.save(foundedUser);
        } catch (BadCredentialsException e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            handleFailedLogin(registerRequest.getName());
            throw new RuntimeException("Invalid credentials");
        }
        return response;
    }

    public RefreshToken refreshToken(RefreshToken refreshTokenRegister) {
        RefreshToken response = new RefreshToken();
        String ourName = jwtUtils.extractUsername(refreshTokenRegister.getToken());
        OurUser ourUser = userRepository.findByUsername(ourName).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRegister.getToken(), ourUser)) {
            String jwt = jwtUtils.generateToken(ourUser);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRegister.getRefreshToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully refreshed token!");
        } else {
            response.setStatusCode(500);
            response.setMessage("Need to authenticate");
        }
        return response;
    }

    private void handleFailedLogin(String name) {
        OurUser user = userRepository.findByUsername(name).orElseThrow();
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= 5) {
            user.setIsAccountNonLocked(false);
        }
        userRepository.save(user);
    }
}
