package com.springboot.blog.controllers;

import com.springboot.blog.payloads.LoginRequest;
import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.security.JwtIssuer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtIssuer jwtIssuer;
    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody @Valid LoginRequest request)
    {
        String token = jwtIssuer.issue(request.getUserId(), request.getEmail(), List.of("USER"));
        return new ResponseEntity<>(new ApiResponse("User logged in sucessfully", true, "accessToken", token), HttpStatus.CREATED);
    }
}
