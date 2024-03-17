package com.springboot.blog.services;

import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.PostsResponse;

import java.util.List;

public interface PostService {
    public PostDto getPost(int id);

    public PostsResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    public PostDto createPost(PostDto postDto, int userId, int categoryId);

    public void deletePost(int id);

    public PostDto updatePost(PostDto postDto, int id);

    public PostsResponse getPostsByCategory(int id, int pageNumber, int pageSize);

    public PostsResponse getPostsByUser(int id, int pageNumber, int pageSize);
    public PostsResponse searchPosts(String title, int pageNumber, int pageSize);

}
