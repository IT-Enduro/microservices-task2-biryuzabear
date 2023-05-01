package com.example.cinemaservice.repository;

import com.example.cinemaservice.model.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    Page<Cinema> findAll(Pageable pageable);
    Cinema findCinemaByCinemaUid(UUID uuid);
}
