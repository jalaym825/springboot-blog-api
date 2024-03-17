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
public class CommentsResponse extends PageResponse{
    private List<CommentDto> comments;

    public CommentsResponse(Page<?> page, List<CommentDto> comments) {
        super(page);
        this.comments = comments;
    }
    public CommentsResponse(Page<?> page) {
        super(page);
    }

}
