package com.example.filmsservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(indexes = @Index(name = "udx_films_film_uid", columnList = "film_uid", unique = true))
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "film_uid")
    private UUID filmUid = UUID.randomUUID();
    @Column(nullable = false)
    private String name;
    @Column(precision = 8, scale = 2, nullable = false, columnDefinition = "NUMERIC(8, 2) DEFAULT 10 CHECK (rating BETWEEN 0 AND 10)")
    private double rating;
    private String director;
    private String producer;
    @Column(nullable = false)
    private String genre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getFilmUid() {
        return filmUid;
    }

    public void setFilmUid(UUID filmUid) {
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
        Film film = (Film) o;
        return Objects.equals(name, film.name) && Objects.equals(director, film.director) && Objects.equals(producer, film.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, director, producer);
    }
}
