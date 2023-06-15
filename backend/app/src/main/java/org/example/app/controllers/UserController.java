package org.example.app.controllers;

import jakarta.validation.Valid;
import org.example.app.controllers.requests.Username;
import org.example.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/userinfo")
    public ResponseEntity<UUID> userWasLogged(@Valid @RequestBody Username username) {
        UUID id = userService.getUserId(username.getUsername());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(id);
    }
}
