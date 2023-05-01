package com.example.cinemaservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class IsPossibleToBuyRequest {
    private UUID cinemaUid;
    private UUID filmUid;
    private LocalDateTime date;

    public UUID getCinemaUid() {
        return cinemaUid;
    }

    public void setCinemaUid(UUID cinemaUid) {
        this.cinemaUid = cinemaUid;
    }

    public UUID getFilmUid() {
        return filmUid;
    }

    public void setFilmUid(UUID filmUid) {
        this.filmUid = filmUid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
