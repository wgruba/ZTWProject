package org.example.app.controllers;

import jakarta.validation.Valid;
import org.example.app.controllers.requests.ticket.CreateTicket;
import org.example.app.controllers.responses.ticket.NewTicket;
import org.example.app.models.Ticket;
import org.example.app.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/buy/ticket")
    public ResponseEntity<NewTicket> buyTicket(@Valid @RequestBody CreateTicket createTicket) {
        Ticket ticket = ticketService.buyTicket(
                createTicket.getUserId(),
                createTicket.getCourseId(),
                createTicket.getPlacesIds(),
                createTicket.getStartStopId(),
                createTicket.getEndStopId(),
                createTicket.getPrice());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(new NewTicket(ticket));
    }
}
