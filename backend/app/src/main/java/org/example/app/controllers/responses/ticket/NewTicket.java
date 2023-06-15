package org.example.app.controllers.responses.ticket;

import lombok.Getter;
import org.example.app.models.Ticket;

import java.util.UUID;


@Getter
public class NewTicket {
    private String ticketConfirmation;
    private UUID ticketId;
    private String username;

    public NewTicket(Ticket ticket) {
        this.ticketId = ticket.getId();
        this.username = ticket.getUser().getUsername();
        this.ticketConfirmation = "Ticket was bought";
    }
}
