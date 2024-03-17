package com.springboot.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String imageName;
    @CreatedDate
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

}
