package org.example.app.controllers;

import org.example.app.exceptions.DomainViolation;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

@SuppressWarnings("unused")
public class FaultController {

    @ResponseBody
    @ExceptionHandler(DomainViolation.class)
    ResponseEntity<?> DVHandler(DomainViolation dv) {
        return ResponseEntity.status(dv.getHttpStatus())
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withStatus(dv.getHttpStatus())
                        .withTitle(dv.getHttpStatus().name())
                        .withDetail(dv.getMessage()));
    }

}
