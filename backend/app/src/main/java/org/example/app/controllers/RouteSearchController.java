package org.example.app.controllers;

import jakarta.validation.Valid;
import org.example.app.controllers.requests.search.CheckAvailability;
import org.example.app.controllers.requests.search.SearchCourse;
import org.example.app.controllers.responses.search.FoundCourse;
import org.example.app.controllers.responses.search.PlaceInfo;
import org.example.app.models.*;
import org.example.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
@SuppressWarnings("unused")
public class RouteSearchController {
    @Autowired
    private CityService cityService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private DistanceService distanceService;

    @GetMapping("/search/city")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(cityService.getAllCities());
    }

    @PostMapping("/search/course")
    public ResponseEntity<List<FoundCourse>> getConnections(@Valid @RequestBody SearchCourse searchCourse) {
        Route route = routeService.getRouteBetweenCities(searchCourse.getStartCity(), searchCourse.getEndCity());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(courseService.getAllCoursesAtRouteAfterDate(route, searchCourse.getDepartureTime())
                        .stream().map(FoundCourse::new).toList());
    }

    @PostMapping("/search/availability")
    public ResponseEntity<List<PlaceInfo>> getPlaces(@Valid @RequestBody CheckAvailability checkAvailability) {
        Distance dummyDistance = distanceService.getDummyDistance(
                checkAvailability.getCityFromId(),
                checkAvailability.getCityToId(),
                checkAvailability.getCourseId()
                );
        List<Place> places = placeService.getPlacesAtCourse(checkAvailability.getCourseId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(places.stream().map(place ->
                                new PlaceInfo(place, placeService.getIntersectingAvailabilities(place, dummyDistance)))
                        .sorted(Comparator.comparingInt(PlaceInfo::getNr))
                        .toList());
    }

}
