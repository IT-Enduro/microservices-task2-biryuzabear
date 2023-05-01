package com.example.cinemaservice.serivce;

import com.example.cinemaservice.dto.CinemaResponse;
import com.example.cinemaservice.dto.CinemaFilmsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CinemaService {
    Page<CinemaResponse> findAll(Pageable pageable);

    CinemaFilmsResponse getFilmSessionsByCinemaUid(UUID cinemaUid);
}
