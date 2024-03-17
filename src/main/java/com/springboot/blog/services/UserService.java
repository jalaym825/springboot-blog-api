package com.springboot.blog.services;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto user);
    public UserDto updateUser(UserDto user, int id);
    public UserDto getUserById(int id);
    public List<UserDto> getAllUsers();
    public void deleteUserById(int id);
}
