package org.example.app.controllers.requests.search;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuppressWarnings("unused")
public class SearchCourse {
    @NotNull
    private UUID startCity;
    @NotNull
    private UUID endCity;
    @NotNull
    private LocalDateTime departureTime;
}
