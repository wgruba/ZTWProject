package org.example.app.exceptions;

import org.springframework.http.HttpStatus;

public class Violations {
    public static DomainViolationBuilder entityNotFound =
            new DomainViolationBuilder("%% with id: %% does not exists", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder routeNotFound =
            new DomainViolationBuilder("Route between cities %% and %% does not exists", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder entityClassNotFound =
            new DomainViolationBuilder("There is no entity of class %% in database", HttpStatus.NOT_FOUND);
    public static DomainViolationBuilder distanceAtCourseNotFound =
            new DomainViolationBuilder("Course doesn't go through cities with ids %% and %%", HttpStatus.BAD_REQUEST);
}
