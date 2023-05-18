package org.example.app.exceptions;

import org.springframework.http.HttpStatus;

public class Violations {
    public static DomainViolationBuilder entityNotFound =
            new DomainViolationBuilder("%% with id: %% does not exists", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder routeNotFound =
            new DomainViolationBuilder("Route between cities %% and %% does not exists", HttpStatus.NOT_FOUND);

}
