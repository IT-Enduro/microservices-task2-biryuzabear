package com.example.cinemaservice.dto;

import com.example.cinemaservice.model.Cinema;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CinemaFilmsResponse {
    private UUID cinemaUid;
    private String name;
    private String address;
    private List<FilmResponse> films;

    public CinemaFilmsResponse() {
    }

    public CinemaFilmsResponse(Cinema cinema, List<FilmResponse> films) {
        this.cinemaUid = cinema.getCinemaUid();
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.films = films;
    }

    public UUID getCinemaUid() {
        return cinemaUid;
    }

    public void setCinemaUid(UUID cinemaUid) {
        this.cinemaUid = cinemaUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FilmResponse> getFilms() {
        return films;
    }

    public void setFilms(List<FilmResponse> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaFilmsResponse that = (CinemaFilmsResponse) o;
        return Objects.equals(cinemaUid, that.cinemaUid) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(films, that.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaUid, name, address, films);
    }
}
