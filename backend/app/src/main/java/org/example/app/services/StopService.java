package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.models.City;
import org.example.app.models.Course;
import org.example.app.models.Stop;
import org.example.app.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StopService {
    @Autowired
    private EntityService entityService;

    public List<Stop> getStopsAtCity(City city) {
        return entityService.stopRepository.findAllByCity(city);
    }

    public LocalDateTime getTimeAtStopFromCourse(Stop stop, Course course) {
        return course.getCourseDate().plusMinutes(stop.getTravellingTimeFromStart());
    }
}
