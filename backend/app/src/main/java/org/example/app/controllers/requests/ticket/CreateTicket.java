package org.example.app.controllers.requests.ticket;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CreateTicket {
    private UUID userId;
    private UUID courseId;
    private List<UUID> placesIds;
    private UUID startStopId;
    private UUID endStopId;
}
