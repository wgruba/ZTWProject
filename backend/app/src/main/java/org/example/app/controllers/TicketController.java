package org.example.app.controllers;

import jakarta.validation.Valid;
import org.example.app.controllers.requests.ticket.CreateTicket;
import org.example.app.controllers.requests.Username;
import org.example.app.controllers.responses.ticket.TicketInfo;
import org.example.app.models.Ticket;
import org.example.app.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@SuppressWarnings("unused")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/buy/ticket")
    public ResponseEntity<String> buyTicket(@Valid @RequestBody CreateTicket createTicket) {
        Ticket ticket = ticketService.buyTicket(
                createTicket.getUsername(),
                createTicket.getCourseId(),
                createTicket.getPlacesIds(),
                createTicket.getStartStopId(),
                createTicket.getEndStopId(),
                createTicket.getPrice());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("Ticket was bought");
    }

    @PostMapping("/history")
    public ResponseEntity<List<TicketInfo>> getTicketHistory(@Valid @RequestBody Username username) {
        List<Ticket> tickets = ticketService.getTicketsByUser(username.getUsername());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(tickets.stream().map(TicketInfo::new).toList());
    }
}
