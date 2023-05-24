package org.example.app.controllers.requests.search;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@SuppressWarnings("unused")
public class CheckAvailability {
    @NotNull
    private UUID courseId;
    @NotNull
    private UUID cityFromId;
    @NotNull
    private UUID cityToId;
}
