package com.example.cinemaservice.repository;

import com.example.cinemaservice.model.Cinema;
import com.example.cinemaservice.model.FilmSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface FilmSessionRepository extends JpaRepository<FilmSession, Integer> {
    List<FilmSession> findFilmSessionsByCinema(Cinema cinema);
    FilmSession findFilmSessionsByCinemaAndFilmUidAndDate(Cinema cinema, UUID filmUid, Timestamp date);
}
