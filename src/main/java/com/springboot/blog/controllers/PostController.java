package com.springboot.blog.controllers;

import com.springboot.blog.configs.AppConstants;
import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.PostsResponse;
import com.springboot.blog.services.FileService;
import com.springboot.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping({"/user/{userId}/category/{categoryId}/posts"})
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDto postDto,
                                                  @PathVariable int userId,
                                                  @PathVariable int categoryId) {
        PostDto post = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post created successfully", true, "post", post), HttpStatus.CREATED);
    }

    @PutMapping({"/posts/{postId}"})
    public ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable int postId) {
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post updated successfully", true, "post", post), HttpStatus.CREATED);
    }


    @GetMapping({"/user/{userId}/posts"})
    public ResponseEntity<ApiResponse> getPostsByUser(@PathVariable int userId,
                                                      @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                      @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize) throws NoSuchFieldException, IllegalAccessException {
        PostsResponse postsByUser = this.postService.getPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, postsByUser), HttpStatus.OK);
    }

    @GetMapping({"/category/{categoryId}/posts"})
    public ResponseEntity<ApiResponse> getPostsByCategory(@PathVariable int categoryId,
                                                          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                          @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize) throws NoSuchFieldException, IllegalAccessException {
        PostsResponse postsByCategory = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, postsByCategory), HttpStatus.OK);
    }

    @GetMapping({"/posts", "/posts/"})
    public ResponseEntity<ApiResponse> getAllPosts(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                   @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
                                                   @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection) throws IllegalAccessException, NoSuchFieldException {
        PostsResponse postsResponse = this.postService.getAllPosts(pageNumber, pageSize < 1 ? 10 : pageSize, sortBy, sortDirection);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, postsResponse), HttpStatus.OK);
    }

    @GetMapping({"/posts/{postId}", "/posts/{postId}/"})
    public ResponseEntity<ApiResponse> getPost(@PathVariable int postId) {
        PostDto post = this.postService.getPost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "post", post), HttpStatus.OK);
    }

    @DeleteMapping({"/posts/{postId}", "/posts/{postId}/"})
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping({"/posts/title/{title}"})
    public ResponseEntity<ApiResponse> getPostsByTitle(@PathVariable String title,
                                                       @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                       @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize) throws NoSuchFieldException, IllegalAccessException {
        PostsResponse postsResponse = this.postService.searchPosts(title, pageNumber, pageSize);
        return new ResponseEntity<>(new ApiResponse(true, postsResponse), HttpStatus.OK);
    }

    @PostMapping({"/posts/image/upload/{postId}"})
    public ResponseEntity<ApiResponse> uploadPostImage(@PathVariable int postId, @RequestParam MultipartFile file) throws IOException {
        PostDto post = this.postService.getPost(postId);
        String fileName = this.fileService.uploadImage(path, file);
        post.setImageName(fileName);
        PostDto postDto = this.postService.updatePost(post, postId);
        return new ResponseEntity<ApiResponse> (new ApiResponse("Photo uploaded successfully", true, "post", postDto), HttpStatus.CREATED);
    }

    @GetMapping(value = {"/posts/{postId}/image"}, produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable int postId, HttpServletResponse response) throws IOException {
        PostDto post = this.postService.getPost(postId);
        InputStream inputStream = this.fileService.getResource(path, post.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }
}
