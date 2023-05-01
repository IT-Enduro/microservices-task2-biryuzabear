package com.example.filmsservice.controller;

import com.example.filmsservice.dto.FilmResponse;
import com.example.filmsservice.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FilmService filmService;

    @Test
    void getAllFilms() throws Exception {
        Pageable pageable = PageRequest.of(1, 10);

        FilmResponse filmResponse1 = new FilmResponse();
        filmResponse1.setFilmUid("123e4567-e89b-12d3-a456-426655440000");
        filmResponse1.setName("The Shawshank Redemption");
        filmResponse1.setRating(9.3);
        filmResponse1.setDirector("Frank Darabont");
        filmResponse1.setProducer("Niki Marvin");
        filmResponse1.setGenre("Drama");

        FilmResponse filmResponse2 = new FilmResponse();
        filmResponse2.setFilmUid("223e4567-e89b-12d3-a456-426655440000");
        filmResponse2.setName("The Godfather");
        filmResponse2.setRating(9.2);
        filmResponse2.setDirector("Francis Ford Coppola");
        filmResponse2.setProducer("Albert S. Ruddy");
        filmResponse2.setGenre("Crime, Drama");

        List<FilmResponse> filmResponseList = new ArrayList<>();
        filmResponseList.add(filmResponse1);
        filmResponseList.add(filmResponse2);

        PageRequest pageRequest = PageRequest.of(1, 10);

        Page<FilmResponse> pageDTO = new PageImpl<>(filmResponseList, pageRequest, filmResponseList.size());

        when(filmService.findAll(pageable)).thenReturn(pageDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/films")
                        .param("page","1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", hasSize(2)))
                .andExpect(jsonPath("$.items[0].name", is(filmResponse1.getName())).exists())
                .andExpect(jsonPath("$.items[0].filmUid", is(filmResponse1.getFilmUid())).exists())
                .andExpect(jsonPath("$.items[1].name", is(filmResponse2.getName())).exists())
                .andExpect(jsonPath("$.items[1].filmUid", is(filmResponse2.getFilmUid())).exists());;
    }


}