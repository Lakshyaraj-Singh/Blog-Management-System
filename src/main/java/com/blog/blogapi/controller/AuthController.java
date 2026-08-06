package com.blog.blogapi.controller;

import com.blog.blogapi.dto.AuthResponse;
import com.blog.blogapi.dto.LoginRequest;
import com.blog.blogapi.security.JwtUtil;
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
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User savedUser = userService.registerUser(request);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        userService.validateLogin(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}