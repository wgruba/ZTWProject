package org.example.app.controllers.responses.ticket;

import org.example.app.models.Ticket;

import java.util.UUID;


public class NewTicket {
    private final String ticketConfirmation = "Ticket was bought";
    private UUID ticketId;
    private String username;

    public NewTicket(Ticket ticket) {
        this.ticketId = ticket.getId();
        this.username = ticket.getUser().getUsername();
    }
}
