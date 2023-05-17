package org.example.app.exceptions;

import org.springframework.http.HttpStatus;

public class DomainViolation extends RuntimeException{
    HttpStatus httpStatus;
    protected DomainViolation(String message, HttpStatus status) {
        super(message);
        httpStatus = status;
    }

    public void throwEx() throws DomainViolation{
        throw this;
    }
}
