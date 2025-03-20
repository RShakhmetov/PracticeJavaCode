package org.example.springmvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springmvc.DTO.User;
import org.example.springmvc.models.UserEntity;
import org.example.springmvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    @JsonView(UserEntity.UserDetails.class)
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/users")
    @JsonView(UserEntity.UserSummary.class)
    public ResponseEntity<List<UserEntity>> getUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@Valid
                                                 @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id,
                                                 @Valid
                                                 @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
