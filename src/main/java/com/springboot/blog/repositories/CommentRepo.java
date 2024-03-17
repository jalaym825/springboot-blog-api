package com.springboot.blog.repositories;

import com.springboot.blog.entities.Comment;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Page<Comment> findByUser(User user, Pageable pageable);
    Page<Comment> findByPost(Post post, Pageable pageable);
}
