package org.example.app.services;

import org.example.app.models.Availability;
import org.example.app.models.Course;
import org.example.app.models.Distance;
import org.example.app.models.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {
    @Autowired
    private EntityService entityService;

    public List<Place> getPlacesAtCourse(UUID courseId) {
        return entityService.placeRepository.findAllByCourse(entityService.getEntityById(courseId, Course.class));
    }

    public List<Availability> getIntersectingAvailabilities(Place place, Distance distance) {
        return entityService.availabilityRepository.findAllByPlace(place).stream().filter(
                availability -> availability.getDistance().checkIfIntersect(distance)).toList();
    }
}
