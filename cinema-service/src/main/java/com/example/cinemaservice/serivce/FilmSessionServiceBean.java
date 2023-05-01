package com.example.cinemaservice.serivce;

import com.example.cinemaservice.dto.IsPossibleToBuyRequest;
import com.example.cinemaservice.model.FilmSession;
import com.example.cinemaservice.repository.CinemaRepository;
import com.example.cinemaservice.repository.FilmSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class FilmSessionServiceBean implements FilmSessionService {
    final RestTemplate restTemplate;
    final FilmSessionRepository filmSessionRepository;
    final CinemaRepository cinemaRepository;

    @Override
    public boolean isPossibleToBuyTicket(IsPossibleToBuyRequest isPossibleToBuyRequest) {
        FilmSession filmSession = getFilmSession(isPossibleToBuyRequest);
        if (filmSession == null || filmSession.getBookedSeats() >= filmSession.getTotalSeats()) {
            return false;
        }
        filmSession.setBookedSeats(filmSession.getBookedSeats() + 1);
        filmSessionRepository.save(filmSession);
        return true;
    }

    private FilmSession getFilmSession(IsPossibleToBuyRequest isPossibleToBuyRequest) {
        FilmSession filmSession = filmSessionRepository.findFilmSessionsByCinemaAndFilmUidAndDate(cinemaRepository.findCinemaByCinemaUid(isPossibleToBuyRequest.getCinemaUid()),
                isPossibleToBuyRequest.getFilmUid(),
                Timestamp.valueOf(isPossibleToBuyRequest.getDate()));
        return filmSession;
    }

    public FilmSessionServiceBean(FilmSessionRepository filmSessionRepository, RestTemplate restTemplate, CinemaRepository cinemaRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.restTemplate = restTemplate;
        this.cinemaRepository = cinemaRepository;
    }
}
