package com.springboot.blog.controllers;

import com.springboot.blog.configs.AppConstants;
import com.springboot.blog.entities.Comment;
import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.CommentDto;
import com.springboot.blog.payloads.CommentsResponse;
import com.springboot.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping({"/user/{userId}/post/{postId}/comment"})
    public ResponseEntity<ApiResponse> postComment(@RequestBody CommentDto commentDto,
                                                   @PathVariable int postId,
                                                   @PathVariable int userId) {
        CommentDto commentDto1 = this.commentService.postComment(commentDto, userId, postId);
        return new ResponseEntity<>(new ApiResponse(true, "comment", commentDto1), HttpStatus.CREATED);
    }

    @DeleteMapping({"/comments/{commentId}"})
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> getComment(@PathVariable int commentId) {
        CommentDto comment = this.commentService.getComment(commentId);
        return new ResponseEntity<>(new ApiResponse(true, "comment", comment), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/comments")
    public ResponseEntity<ApiResponse> getCommentsByUserId(@PathVariable int userId,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize) throws NoSuchFieldException, IllegalAccessException {
        CommentsResponse commentsByUserId = this.commentService.getCommentsByUserId(userId, pageNumber, pageSize);
        return new ResponseEntity<>(new ApiResponse(true, commentsByUserId), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<ApiResponse> getCommentsByPostId(@PathVariable int postId,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
                                                           @RequestParam(defaultValue = AppConstants.PAGE_SIZE) int pageSize) throws NoSuchFieldException, IllegalAccessException {
        CommentsResponse commentsByUserId = this.commentService.getCommentsByPostId(postId, pageNumber, pageSize);
        return new ResponseEntity<>(new ApiResponse(true, commentsByUserId), HttpStatus.OK);
    }
}
