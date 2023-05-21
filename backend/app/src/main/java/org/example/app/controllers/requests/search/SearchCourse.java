package org.example.app.controllers.requests.search;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class SearchCourse {
    private UUID startCity;
    private UUID endCity;
    private LocalDateTime departureTime;
}
