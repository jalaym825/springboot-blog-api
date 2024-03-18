package com.springboot.blog.controllers;

import com.springboot.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/users/login/ok")
    public ResponseEntity<ApiResponse> hello()
    {
        return new ResponseEntity<ApiResponse>(new ApiResponse("Hi", true), HttpStatus.OK);
    }
}
