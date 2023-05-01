package com.example.filmsservice.service;

import com.example.filmsservice.dto.FilmResponse;
import com.example.filmsservice.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceBean implements FilmService {

    final
    FilmRepository filmRepository;

    public FilmServiceBean(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Page<FilmResponse> findAll(Pageable pageable) {
        return filmRepository.findAll(pageable).map(FilmResponse::new);
    }

}
