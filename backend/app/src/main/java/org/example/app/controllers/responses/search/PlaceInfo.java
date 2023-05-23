package org.example.app.controllers.responses.search;

import lombok.Getter;
import org.example.app.models.Availability;
import org.example.app.models.Place;

import java.util.List;
import java.util.UUID;

@Getter
public class PlaceInfo {
    private UUID placeId;
    private boolean isAvailable;

    public PlaceInfo(Place place, List<Availability> occupiedOnDistance) {
        this.placeId = place.getId();
        this.isAvailable = occupiedOnDistance.isEmpty();
    }
}
