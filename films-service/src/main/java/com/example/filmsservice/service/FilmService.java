package com.example.filmsservice.service;

import com.example.filmsservice.dto.FilmResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {
    Page<FilmResponse> findAll(Pageable pageable);
}
