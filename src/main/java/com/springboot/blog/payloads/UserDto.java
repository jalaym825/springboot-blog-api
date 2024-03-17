package com.springboot.blog.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must contains more than 4 characters")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
//    private List<PostDto> posts;

}
