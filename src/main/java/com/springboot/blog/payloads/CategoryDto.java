package com.springboot.blog.payloads;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int id;
    @NotBlank(message = "Category title is required")
    private String title;
    @NotBlank(message = "Category description is required")
    private String description;
//    private List<PostDto> posts;
}
