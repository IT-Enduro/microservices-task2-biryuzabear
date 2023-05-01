package com.example.filmsservice.controller;

import com.example.filmsservice.dto.FilmResponse;
import com.example.filmsservice.dto.PageResponse;
import com.example.filmsservice.service.FilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<PageResponse<FilmResponse>> getAllFilms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<FilmResponse> filmPage = filmService.findAll(pageable);

        return ResponseEntity.ok().body(new PageResponse<>(filmPage));
    }


}

