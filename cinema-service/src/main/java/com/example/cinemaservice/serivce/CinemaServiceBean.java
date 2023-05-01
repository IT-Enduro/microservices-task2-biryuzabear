package com.example.cinemaservice.serivce;

import com.example.cinemaservice.dto.CinemaResponse;
import com.example.cinemaservice.dto.CinemaFilmsResponse;
import com.example.cinemaservice.dto.FilmResponse;
import com.example.cinemaservice.dto.PageFilmResponse;
import com.example.cinemaservice.model.Cinema;
import com.example.cinemaservice.model.FilmSession;
import com.example.cinemaservice.repository.CinemaRepository;
import com.example.cinemaservice.repository.FilmSessionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaServiceBean implements CinemaService {

    private final String GET_FILMS_URL;
    final
    CinemaRepository cinemaRepository;
    final
    FilmSessionRepository filmSessionRepository;
    final
    RestTemplate restTemplate;

    public CinemaServiceBean(CinemaRepository cinemaRepository,
                             FilmSessionRepository filmSessionRepository,
                             RestTemplate restTemplate,
                             @Value("${app.films.get.url}") String getFilmsUrl) {
        this.cinemaRepository = cinemaRepository;
        this.filmSessionRepository = filmSessionRepository;
        this.restTemplate = restTemplate;
        this.GET_FILMS_URL = getFilmsUrl;
    }

    @Override
    public Page<CinemaResponse> findAll(Pageable pageable) {
        return cinemaRepository.findAll(pageable).map(CinemaResponse::new);
    }

    @Override
    public CinemaFilmsResponse getFilmSessionsByCinemaUid(UUID cinemaUid) {
        Cinema cinema = cinemaRepository.findCinemaByCinemaUid(cinemaUid);
        if(cinema == null)
            return null;
        List<FilmSession> sessions = filmSessionRepository.findFilmSessionsByCinema(cinema);
        List<FilmSession> distinctByFilms = sessions
                .stream()
                .map(FilmSession::getFilmUid)
                .distinct()
                .map(uuid -> sessions.stream()
                        .filter(session -> session.getFilmUid().equals(uuid))
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        PageFilmResponse films = restTemplate.getForObject(GET_FILMS_URL, PageFilmResponse.class);
        List<FilmResponse> filmList = films.getItems().stream()
                .filter(filmResponse -> distinctByFilms.stream()
                        .anyMatch(session -> session.getFilmUid().toString().equals(filmResponse.getFilmUid())))
                .toList();
        return new CinemaFilmsResponse(cinema, filmList);
    }
}
