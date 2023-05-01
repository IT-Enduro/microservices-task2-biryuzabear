package com.example.cinemaservice.dto;

import java.util.Objects;

public class FilmResponse {

    private String filmUid;
    private String name;
    private double rating;
    private String director;
    private String producer;
    private String genre;

    public FilmResponse() {
    }

    public String getFilmUid() {
        return filmUid;
    }

    public void setFilmUid(String filmUid) {
        this.filmUid = filmUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmResponse filmResponse = (FilmResponse) o;
        return Double.compare(filmResponse.rating, rating) == 0 && Objects.equals(filmUid, filmResponse.filmUid) && Objects.equals(name, filmResponse.name) && Objects.equals(director, filmResponse.director) && Objects.equals(producer, filmResponse.producer) && Objects.equals(genre, filmResponse.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmUid, name, rating, director, producer, genre);
    }
}
