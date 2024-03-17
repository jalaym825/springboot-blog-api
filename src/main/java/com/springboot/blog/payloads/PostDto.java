package com.springboot.blog.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int id;
    @NotBlank(message = "Provide title")
    private String title;
    @NotBlank(message = "Provide content")
    private String content;
    private String imageName;
    @CreatedDate
    private Date date;
    private UserDto user;
    private CategoryDto category;

}
