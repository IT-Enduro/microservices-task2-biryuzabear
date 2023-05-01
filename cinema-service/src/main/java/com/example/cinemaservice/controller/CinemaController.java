package com.example.cinemaservice.controller;

import com.example.cinemaservice.dto.CinemaResponse;
import com.example.cinemaservice.dto.CinemaFilmsResponse;
import com.example.cinemaservice.dto.MessageResponse;
import com.example.cinemaservice.dto.PageResponse;
import com.example.cinemaservice.serivce.CinemaService;
import com.example.cinemaservice.serivce.CinemaServiceBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaServiceBean cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cinema")
    public ResponseEntity<PageResponse<CinemaResponse>> getAllFilms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<CinemaResponse> cinemaPage = cinemaService.findAll(pageable);

        return ResponseEntity.ok().body(new PageResponse<>(cinemaPage));
    }

    @GetMapping("/cinema/{cinemaUid}/films")
    public ResponseEntity<Object> getAllFilms(@PathVariable UUID cinemaUid) {
        CinemaFilmsResponse cinemaFilms = cinemaService.getFilmSessionsByCinemaUid(cinemaUid);
        if (cinemaFilms == null) {
            return new ResponseEntity<>(new MessageResponse("Кинотеатр не найден"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(cinemaFilms);
        }
    }


}