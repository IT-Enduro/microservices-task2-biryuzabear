package com.example.cinemaservice.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int pageSize;
    private int totalElements;

    public PageResponse() {
    }

    public PageResponse(Page<T> page) {
        this.page = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalPages();
        this.items = page.getContent();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
