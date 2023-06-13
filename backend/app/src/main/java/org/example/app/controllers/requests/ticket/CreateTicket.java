package org.example.app.controllers.requests.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuppressWarnings("unused")
public class CreateTicket {

    @NotNull
    private String username;
    @NotNull
    private UUID courseId;
    @NotNull
    private List<UUID> placesIds;
    @NotNull
    private UUID startStopId;
    @NotNull
    private UUID endStopId;
    @NotNull
    private double price;
}
