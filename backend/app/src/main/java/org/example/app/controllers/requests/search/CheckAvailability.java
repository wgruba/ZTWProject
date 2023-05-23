package org.example.app.controllers.requests.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CheckAvailability {
    private UUID courseId;
    private UUID cityFromId;
    private UUID cityToId;
}
