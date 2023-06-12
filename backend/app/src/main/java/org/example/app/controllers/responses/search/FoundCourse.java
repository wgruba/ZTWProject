package org.example.app.controllers.responses.search;

import lombok.Getter;
import org.example.app.models.Course;
import org.example.app.models.Stop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class FoundCourse {
    private final UUID id;
    private final LocalDateTime departureDate;
    private final List<StopInConnection> stops;
    private final String routeName;
    public FoundCourse(Course course, List<Stop> stops) {
        this.id = course.getId();
        this.departureDate = course.getCourseDate();
        this.stops = stops.stream().map(stop -> new StopInConnection(stop, course.getCourseDate())).toList();
        this.routeName = course.getRoute().getName();
    }

    @Getter
    private static class StopInConnection {
        private final UUID id;
        private final LocalDateTime arrivalDate;
        private final String cityName;
        public StopInConnection(Stop stop, LocalDateTime departureDate) {
            this.id = stop.getId();
            this.arrivalDate = departureDate.plusMinutes(stop.getTravellingTimeFromStart());
            this.cityName = stop.getCity().getName();
        }
    }
}
