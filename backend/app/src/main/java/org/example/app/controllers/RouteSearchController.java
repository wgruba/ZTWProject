package org.example.app.controllers;

import org.example.app.controllers.requests.search.SearchCourse;
import org.example.app.models.City;
import org.example.app.models.Course;
import org.example.app.models.Route;
import org.example.app.services.CityService;
import org.example.app.services.CourseService;
import org.example.app.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RouteSearchController {
    @Autowired
    private CityService cityService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/search/city")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(cityService.getAllCities());
    }

    @GetMapping("/search/course")
    public ResponseEntity<List<Course>> getConnections(@Validated @RequestBody SearchCourse searchCourse) {
        Route route = routeService.getRouteBetweenCities(searchCourse.getStartCity(), searchCourse.getEndCity());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(courseService.getAllCoursesAtRouteAfterDate(route, searchCourse.getDepartureTime()));
    }

}
