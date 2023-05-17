package org.example.app.exceptions;

import org.springframework.http.HttpStatus;

public class Violations {
    public static DomainViolationBuilder entityNotFound =
            new DomainViolationBuilder("%% with id: %% does not exists", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder routeNotFound =
            new DomainViolationBuilder("Route between cities %% and %% does not exists", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder differentSizeOfCitiesAndTimes =
            new DomainViolationBuilder("You provided %% cities and %% travelling times. Sizes have to be equal", HttpStatus.EXPECTATION_FAILED);
    public static DomainViolationBuilder notEnoughCities =
            new DomainViolationBuilder("There need to be at least 2 cities to create a route. You provided %%", HttpStatus.EXPECTATION_FAILED);
}
