package com.example.ticketsservice.repository;

import com.example.ticketsservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findByTicketUid(UUID ticketUid);
}
