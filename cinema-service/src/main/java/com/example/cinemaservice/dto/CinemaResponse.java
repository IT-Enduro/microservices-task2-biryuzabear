package com.example.cinemaservice.dto;

import com.example.cinemaservice.model.Cinema;

import java.util.Objects;
import java.util.UUID;

public class CinemaResponse {
    private UUID cinemaUid;
    private String name;
    private String address;

    public CinemaResponse() {
    }

    public CinemaResponse(Cinema cinema) {
        this.cinemaUid = cinema.getCinemaUid();
        this.address = cinema.getAddress();
        this.name = cinema.getName();
    }

    public UUID getCinemaUid() {
        return cinemaUid;
    }

    public void setCinemaUid(UUID cinemaUid) {
        this.cinemaUid = cinemaUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaResponse cinemaResponse = (CinemaResponse) o;
        return Objects.equals(cinemaUid, cinemaResponse.cinemaUid) && Objects.equals(name, cinemaResponse.name) && Objects.equals(address, cinemaResponse.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaUid, name, address);
    }
}
