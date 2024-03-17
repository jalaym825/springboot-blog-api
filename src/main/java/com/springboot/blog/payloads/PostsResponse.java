package com.springboot.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PostsResponse extends PageResponse{
    private List<PostDto> posts;

    public PostsResponse(Page<?> page, List<PostDto> posts) {
        super(page);
        this.posts = posts;
    }
    public PostsResponse(Page<?> page) {
        super(page);
    }

}
