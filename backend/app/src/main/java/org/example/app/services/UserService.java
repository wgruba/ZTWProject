package org.example.app.services;

import org.example.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private EntityService entityService;

    public UUID getUserId(String username) {
        return getUser(username).getId();
    }

    public User getUser(String username) {
        return entityService.userRepository.findAllByUsername(username).stream().findFirst().orElse(
                entityService.save(new User(username, ""))
        );
    }
}
