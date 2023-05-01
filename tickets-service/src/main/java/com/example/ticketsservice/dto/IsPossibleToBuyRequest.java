package com.example.ticketsservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class IsPossibleToBuyRequest {
    private UUID cinemaUid;
    private UUID filmUid;
    private LocalDateTime date;

    public IsPossibleToBuyRequest(UUID cinemaUid, UUID filmUid, LocalDateTime date) {
        this.cinemaUid = cinemaUid;
        this.filmUid = filmUid;
        this.date = date;
    }

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
