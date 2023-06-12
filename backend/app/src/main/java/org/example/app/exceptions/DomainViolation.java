package org.example.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public class DomainViolation extends RuntimeException{

    @Getter
    HttpStatus httpStatus;
    protected DomainViolation(String message, HttpStatus status) {
        super(message);
        httpStatus = status;
    }

    protected void throwEx() throws DomainViolation{
        throw this;
    }

    public void throwExIf(boolean condition) {
        if (condition)
            throw this;
    }
}
