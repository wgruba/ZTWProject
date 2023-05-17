package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.models.Route;
import org.example.app.models.Stop;
import org.example.app.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteBetweenStops(Stop starStop, Stop endStop) {
        return getAllRoutes().stream().filter(route -> {
            List<Stop> stops = route.orderedStops();
            return (stops.indexOf(starStop) - stops.indexOf(endStop) <= 0) &&
                    stops.contains(starStop) && stops.contains(endStop);
        }).findFirst();
    }
}
