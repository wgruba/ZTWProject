package org.example.app.controllers.requests.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CreateTicket {
    private UUID userId;
    private UUID courseId;
    private List<UUID> placesIds;
    private UUID startStopId;
    private UUID endStopId;
}
