package com.trevis.startup.dto;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaginatedListResponse<T> {
    
    public List<T> data;
    public Long page;
    public Long totalPages;
}
