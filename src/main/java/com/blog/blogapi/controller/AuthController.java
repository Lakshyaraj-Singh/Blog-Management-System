package com.blog.blogapi.controller;

import com.blog.blogapi.dto.RegisterRequest;
import com.blog.blogapi.entity.User;
import com.blog.blogapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User savedUser = userService.registerUser(request);
        return ResponseEntity.ok(savedUser);
    }
}