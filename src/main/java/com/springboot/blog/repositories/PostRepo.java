package com.springboot.blog.repositories;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    public Page<Post> findByUser(User user, PageRequest pageRequest);
    public Page<Post> findByCategory(Category category, PageRequest pageRequest);
    public Page<Post> findByTitleContaining(String content, PageRequest pageRequest);
}
