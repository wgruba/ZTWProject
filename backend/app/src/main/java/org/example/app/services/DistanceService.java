package org.example.app.services;

import org.example.app.models.Course;
import org.example.app.models.Distance;
import org.example.app.models.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.example.app.exceptions.Violations.distanceAtCourseNotFound;

@Service
public class DistanceService {
    @Autowired
    private EntityService entityService;

    public Distance getDummyDistance(UUID cityFromId, UUID cityToId, UUID courseId) {
        Course course = entityService.getEntityById(courseId, Course.class);
        List<Stop> stops = course.getRoute().orderedStops().stream().filter(stop ->
                stop.getCity().idEqualsAnyOf(cityFromId, cityToId)).toList();
        if (stops.size() != 2)
            distanceAtCourseNotFound.throwEx(cityFromId.toString(), cityToId.toString());
        return new Distance(stops.get(0), stops.get(1));
    }
}
