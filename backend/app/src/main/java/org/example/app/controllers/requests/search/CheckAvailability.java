package org.example.app.controllers.requests.search;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CheckAvailability {
    private UUID courseId;
    private UUID cityFromId;
    private UUID cityToId;
}
