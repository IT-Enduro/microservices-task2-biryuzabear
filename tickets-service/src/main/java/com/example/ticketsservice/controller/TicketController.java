package com.example.ticketsservice.controller;

import com.example.ticketsservice.dto.MessageResponse;
import com.example.ticketsservice.dto.TicketResponse;
import com.example.ticketsservice.dto.TicketPurchaseRequest;
import com.example.ticketsservice.service.TicketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/tickets/cinema/{cinemaUid}/films/{filmUid}")
    public ResponseEntity<Object> purchaseTicket(@PathVariable UUID cinemaUid, @PathVariable UUID filmUid, @RequestHeader("X-User-Name") String userName, @RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        UUID ticketUid = ticketService.purchaseTicket(cinemaUid, filmUid, userName, ticketPurchaseRequest);
        if (ticketUid == null) {
            return new ResponseEntity<>(new MessageResponse("Не удалось купить билет"), HttpStatus.NOT_FOUND);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{ticketUid}").buildAndExpand(ticketUid).toUri());
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }

    @GetMapping("/tickets/{ticketUid}")
    public ResponseEntity<Object> getTicketInformation(@PathVariable UUID ticketUid, @RequestHeader("X-User-Name") String userName) {
        TicketResponse ticketResponse = ticketService.getTicket(ticketUid, userName);
        if (ticketResponse == null) {
            return new ResponseEntity<>(new MessageResponse("Билет не найден"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(ticketResponse);
        }
    }

    @DeleteMapping("/tickets/{ticketUid}")
    public ResponseEntity<Object> returnTicket(@PathVariable UUID ticketUid, @RequestHeader("X-User-Name") String userName) {
        Boolean returned = ticketService.returnTicket(ticketUid, userName);
        if (returned == null) {
            return new ResponseEntity<>(new MessageResponse("Билет не найден"), HttpStatus.NOT_FOUND);
        } else if(returned) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(new MessageResponse("Уже нельзя вернуть билет"), HttpStatus.CONFLICT);
        }
   }
}
