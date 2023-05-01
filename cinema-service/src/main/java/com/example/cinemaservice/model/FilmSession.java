package com.example.cinemaservice.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(indexes = @Index(name = "udx_film_session_session_uid", columnList = "session_uid", unique = true))
public class FilmSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_uid", nullable = false)
    private UUID sessionUid = UUID.randomUUID();
    @Column(nullable = false)
    private UUID filmUid;
    @Column(nullable = false)
    private Integer totalSeats;
    @Column(nullable = false, columnDefinition = "INT NOT NULL DEFAULT 0 CHECK (booked_seats < total_seats)")
    private Integer bookedSeats;
    @Column(nullable = false)
    private Timestamp date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Cinema cinema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSessionUid() {
        return sessionUid;
    }

    public void setSessionUid(UUID sessionUid) {
        this.sessionUid = sessionUid;
    }

    public UUID getFilmUid() {
        return filmUid;
    }

    public void setFilmUid(UUID filmUid) {
        this.filmUid = filmUid;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Integer bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmSession that = (FilmSession) o;
        return Objects.equals(filmUid, that.filmUid) && Objects.equals(date, that.date) && Objects.equals(cinema, that.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmUid, date, cinema);
    }
}