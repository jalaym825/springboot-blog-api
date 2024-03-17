package com.springboot.blog.payloads;


import lombok.*;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageResponse {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public PageResponse(Page<?> page) {
        this.setPageNumber(page.getNumber());
        this.setTotalPages(page.getTotalPages());
        this.setPageSize(page.getSize());
        this.setTotalElements(page.getTotalElements());
        this.setLastPage(page.isLast());
    }
}
