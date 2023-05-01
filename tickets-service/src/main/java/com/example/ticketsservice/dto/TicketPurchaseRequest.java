package com.example.ticketsservice.dto;

import java.time.LocalDateTime;

public class TicketPurchaseRequest {
    private LocalDateTime date;
    private int row;
    private int seat;

    public TicketPurchaseRequest(LocalDateTime date, int row, int seat) {
        this.date = date;
        this.row = row;
        this.seat = seat;
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
