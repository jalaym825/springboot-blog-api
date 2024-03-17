package com.springboot.blog.services.impl;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.payloads.PostDto;
import com.springboot.blog.payloads.PostsResponse;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.repositories.PostRepo;
import com.springboot.blog.repositories.UserRepo;
import com.springboot.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto getPost(int id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return this.postToDto(post);
    }

    @Override
    public PostsResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ?  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = this.postRepo.findAll(pageRequest);
        List<Post> posts = postPage.getContent();

        return new PostsResponse(postPage, posts.stream().map(this::postToDto).collect(Collectors.toList()));
    }

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found", "id", categoryId));

        Post post = this.dtoToPost(postDto);
        post.setCategory(category);
        post.setUser(user);

        Post saved = this.postRepo.save(post);

        return this.postToDto(saved);
    }

    @Override
    public void deletePost(int id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postDto.setId(id);
        Post saved = this.postRepo.save(this.dtoToPost(postDto));
        return this.postToDto(saved);
    }

    @Override
    public PostsResponse getPostsByCategory(int id, int pageNumber, int pageSize) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = this.postRepo.findByCategory(category, pageRequest);
        return new PostsResponse(page, page.map(this::postToDto).toList());
    }

    @Override
    public PostsResponse getPostsByUser(int id, int pageNumber, int pageSize) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = this.postRepo.findByUser(user, pageRequest);
        return new PostsResponse(page, page.map(this::postToDto).toList());
    }

    @Override
    public PostsResponse searchPosts(String title, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = this.postRepo.findByTitleContaining(title, pageRequest);
        return new PostsResponse(postPage, postPage.map(this::postToDto).toList());
    }

    private Post dtoToPost(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    private PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }

}
