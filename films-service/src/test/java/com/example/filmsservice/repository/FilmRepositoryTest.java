package com.example.filmsservice.repository;

import com.example.filmsservice.model.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
class FilmRepositoryTest {

    @Autowired
    FilmRepository filmRepository;

    @Test
    void findAll() {
        Film film1 = new Film();
        film1.setName("The Shawshank Redemption");
        film1.setRating(9.3);
        film1.setDirector("Frank Darabont");
        film1.setProducer("Niki Marvin");
        film1.setGenre("Drama");

        filmRepository.save(film1);

        Film film2 = new Film();
        film2.setName("The Godfather");
        film2.setRating(9.2);
        film2.setDirector("Francis Ford Coppola");
        film2.setProducer("Albert S. Ruddy");
        film2.setGenre("Crime, Drama");

        filmRepository.save(film2);


        assertNotNull(film1.getFilmUid());
        assertNotNull(film2.getFilmUid());
        assertNotEquals(film1.getFilmUid(),film2.getFilmUid());

        assertEquals( 1, film1.getId());
        assertEquals( 2, film2.getId());

        Pageable pageable = PageRequest.of(1, 10);

        assertEquals(filmRepository.findAll(pageable).getTotalElements(), 2);

    }
}