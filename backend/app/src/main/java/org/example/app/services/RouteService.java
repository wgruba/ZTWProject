package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.exceptions.Violations;
import org.example.app.models.City;
import org.example.app.models.Route;
import org.example.app.models.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.app.exceptions.Violations.startEndCitiesMustDiffer;

@Service
@AllArgsConstructor
public class RouteService {
    @Autowired
    private EntityService entityService;

    public List<Route> getAllRoutes() throws DomainViolation{
        return entityService.getAll(Route.class);
    }


    public Route getRouteBetweenCities(UUID startCity, UUID endCity) throws DomainViolation {
        startEndCitiesMustDiffer.throwExIf(startCity.equals(endCity));
        return getRouteBetweenCities(
                getCity(startCity),
                getCity(endCity)
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

    public List<Stop> getStopsBetweenCitiesAtRoute(Route route, UUID startCityId, UUID endCityId) {
        List<Stop> stops = route.orderedStops();
        List<Stop> startEndStops = stops.stream().filter(stop -> stop.getCity().idEqualsAnyOf(startCityId, endCityId)).toList();
        if (startEndStops.size() < 2)
            return List.of();
        Stop startStop = startEndStops.get(0);
        Stop endStop = startEndStops.get(1);
        return stops.subList(stops.indexOf(startStop), stops.indexOf(endStop) + 1);
    }

    private City getCity(UUID cityId) {
        return entityService.getEntityById(cityId, City.class);
    }
}
