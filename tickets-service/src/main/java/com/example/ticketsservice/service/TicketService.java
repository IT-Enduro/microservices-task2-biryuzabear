package com.example.ticketsservice.service;

import com.example.ticketsservice.dto.TicketResponse;
import com.example.ticketsservice.dto.TicketPurchaseRequest;

import java.util.UUID;

public interface TicketService {
    UUID purchaseTicket(UUID cinemaUid, UUID filmUid, String userName, TicketPurchaseRequest ticketPurchaseRequest);

    TicketResponse getTicket(UUID ticketUid, String userName);

    Boolean returnTicket(UUID ticketUid, String userName);
}
