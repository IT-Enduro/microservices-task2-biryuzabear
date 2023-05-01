package com.example.ticketsservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;



@Entity
@Table(indexes = @Index(name = "udx_tickets_ticket_uid", columnList = "ticket_uid", unique = true))
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket_uid")
    private UUID ticketUid = UUID.randomUUID();
    private UUID filmUid;
    private UUID cinemaUid;
    private String userName;
    private Timestamp date;
    @Pattern(regexp = "^(BOOKED|CANCELED)$")
    private String status = "BOOKED";
    private int row;
    private int seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTicketUid() {
        return ticketUid;
    }

    public void setTicketUid(UUID ticketUid) {
        this.ticketUid = ticketUid;
    }

    public UUID getFilmUid() {
        return filmUid;
    }

    public void setFilmUid(UUID filmUid) {
        this.filmUid = filmUid;
    }

    public UUID getCinemaUid() {
        return cinemaUid;
    }

    public void setCinemaUid(UUID sessionUid) {
        this.cinemaUid = sessionUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(filmUid, ticket.filmUid) && Objects.equals(cinemaUid, ticket.cinemaUid) && Objects.equals(userName, ticket.userName) && Objects.equals(date, ticket.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmUid, cinemaUid, userName, date);
    }
}
