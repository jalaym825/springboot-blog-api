package com.springboot.blog.controllers;

import com.springboot.blog.entities.User;
import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //    create user
    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = this.userService.createUser(userDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User created successfully", true, "user", userDto1), HttpStatus.CREATED);
    }

    //    get user
    @GetMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<ApiResponse> getUser(@PathVariable("userId") int userId) throws Exception {
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "user", userDto), HttpStatus.OK);
    }

    //    get all users
    @GetMapping({"/", ""})
    public ResponseEntity<ApiResponse> getAllUsers() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "users", this.userService.getAllUsers()), HttpStatus.OK);
    }

    //    updating user
    @PutMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<ApiResponse> updateUser(@Validated @RequestBody UserDto userDto, @PathVariable("userId") int userId) {
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "user", userDto1), HttpStatus.OK);
    }

    //    delete user
    @DeleteMapping({"/{userId}"})
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int userId) {
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
}
