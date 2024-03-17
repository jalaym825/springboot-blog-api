package com.springboot.blog.services;

import com.springboot.blog.payloads.CommentDto;
import com.springboot.blog.payloads.CommentsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface CommentService {
    public CommentDto postComment(CommentDto commentDto, int userId, int postId);

    public void deleteComment(int commentId);

    public CommentsResponse getCommentsByUserId(int userId, int pageNumber, int pageSize);

    public CommentsResponse getCommentsByPostId(int postId, int pageNumber, int pageSize);

    public CommentDto getComment(int commentId);
}
