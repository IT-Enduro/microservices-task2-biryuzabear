package com.example.ticketsservice.dto;

import com.example.ticketsservice.model.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketResponse {
    private UUID ticketUid;
    private String status;
    private LocalDateTime date;
    private int row;
    private int seat;

    public TicketResponse(Ticket ticket) {
        this.ticketUid = ticket.getTicketUid();
        this.status = ticket.getStatus();
        this.date = ticket.getDate().toLocalDateTime();
        this.row = ticket.getRow();
        this.seat = ticket.getSeat();
    }

    public UUID getTicketUid() {
        return ticketUid;
    }

    public void setTicketUid(UUID ticketUid) {
        this.ticketUid = ticketUid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
