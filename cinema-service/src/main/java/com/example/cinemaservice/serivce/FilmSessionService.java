package com.example.cinemaservice.serivce;

import com.example.cinemaservice.dto.IsPossibleToBuyRequest;

public interface FilmSessionService {
    boolean isPossibleToBuyTicket(IsPossibleToBuyRequest isPossibleToBuyRequest);
}
