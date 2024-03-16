package com.springboot.blog.services;

import com.springboot.blog.entities.User;
import com.springboot.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, int id);
    UserDto getUserById(int id);
    List<User> getAllUsers();
    void deleteUserById(int id);
}
