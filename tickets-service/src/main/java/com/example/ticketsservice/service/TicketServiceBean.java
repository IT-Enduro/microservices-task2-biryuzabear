package com.example.ticketsservice.service;

import com.example.ticketsservice.dto.IsPossibleToBuyRequest;
import com.example.ticketsservice.dto.TicketPurchaseRequest;
import com.example.ticketsservice.dto.TicketResponse;
import com.example.ticketsservice.model.Ticket;
import com.example.ticketsservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketServiceBean implements TicketService {

    private final String IS_POSSIBLE_TO_BUY_URL;
    final
    TicketRepository ticketRepository;
    final
    RestTemplate restTemplate;

    public TicketServiceBean(TicketRepository ticketRepository,
                             RestTemplate restTemplate,
                             @Value("${app.tickets.is-possible-to-buy.url}") String isPossibleToBuy) {
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;
        IS_POSSIBLE_TO_BUY_URL = isPossibleToBuy;
    }

    @Override
    public UUID purchaseTicket(UUID cinemaUid, UUID filmUid, String userName, TicketPurchaseRequest ticketPurchaseRequest) {
        IsPossibleToBuyRequest request = new IsPossibleToBuyRequest(cinemaUid, filmUid, ticketPurchaseRequest.getDate());
        Boolean isPossibleToBuy = restTemplate.postForObject(IS_POSSIBLE_TO_BUY_URL, request, Boolean.class);
        if (!isPossibleToBuy) {
            return null;
        } else {
            Ticket ticket = new Ticket();
            ticket.setDate(Timestamp.valueOf(ticketPurchaseRequest.getDate()));
            ticket.setRow(ticketPurchaseRequest.getRow());
            ticket.setFilmUid(filmUid);
            ticket.setCinemaUid(cinemaUid);
            ticket.setUserName(userName);
            ticket.setSeat(ticketPurchaseRequest.getSeat());
            ticketRepository.save(ticket);
            return ticket.getTicketUid();
        }
    }

    @Override
    public TicketResponse getTicket(UUID ticketUid, String userName) {
        Ticket ticket = ticketRepository.findByTicketUid(ticketUid);
        if (ticket == null) {
            return null;
        } else {
            return new TicketResponse(ticket);
        }
    }

    @Override
    public Boolean returnTicket(UUID ticketUid, String userName) {
        Ticket ticket = ticketRepository.findByTicketUid(ticketUid);
        if (ticket == null) {
            return null;
        }
        Boolean isPossibleToReturn = (Timestamp.valueOf(LocalDateTime.now()).getTime() - ticket.getDate().getTime()) / 3600 / 1000 < -1;
        if (isPossibleToReturn) {
            ticket.setStatus("CANCELED");
            ticketRepository.save(ticket);
        }
        return isPossibleToReturn;
    }
}
