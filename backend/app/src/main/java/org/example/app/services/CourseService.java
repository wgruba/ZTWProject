package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.models.Course;
import org.example.app.models.Route;
import org.example.app.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCoursesAtRoute(Route route) {
        return courseRepository.findAllByRoute(route);
    }

    public List<Course> getAllCoursesAtRouteAfterDate(Route route, LocalDateTime dateTime) {
        return getAllCoursesAtRoute(route).stream().filter(course -> course.getCourseDate().isAfter(dateTime)).toList();
    }
}
