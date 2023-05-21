package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.exceptions.Violations;
import org.example.app.models.City;
import org.example.app.models.Route;
import org.example.app.models.Stop;
import org.example.app.repositories.RouteRepository;
import org.example.app.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RouteService {
    @Autowired
    private EntityService entityService;

    public List<Route> getAllRoutes() throws DomainViolation{
        return entityService.getAll(Route.class);
    }


    public Route getRouteBetweenCities(UUID startCity, UUID endCity) throws DomainViolation {
        return getRouteBetweenCities(
                entityService.getEntityById(startCity, City.class),
                entityService.getEntityById(endCity, City.class)
        );
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private Route getRouteBetweenCities(City startCity, City endCity) throws DomainViolation {
        Optional<Route> maybeRoute = getAllRoutes().stream().filter(route -> {
            List<City> cities = route.orderedStops().stream().map(Stop::getCity).toList();
            return (cities.indexOf(startCity) - cities.indexOf(endCity) <= 0) &&
                    cities.contains(startCity) && cities.contains(endCity);
        }).findFirst();
        if (maybeRoute.isEmpty())
            Violations.routeNotFound.throwEx(startCity.getName(), endCity.getName());
        return maybeRoute.get();
    }
}
