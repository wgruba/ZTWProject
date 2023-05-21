package org.example.app.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class DomainViolationBuilder {
    private String message;
    private final HttpStatus httpStatus;

    public DomainViolationBuilder(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private DomainViolation build(String...args) {
        Arrays.stream(args).forEach(this::replaceNext);
        message = message.replaceAll("%%", "EMPTY");
        return new DomainViolation(message, httpStatus);
    }

    public void throwEx(String...args) throws DomainViolation {
        build(args).throwEx();
    }

    private void replaceNext(String arg) {
        message = message.replaceFirst("%%", arg);
    }
}
