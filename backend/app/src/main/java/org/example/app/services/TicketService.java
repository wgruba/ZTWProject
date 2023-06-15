package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {
    @Autowired
    private EntityService entityService;
    @Autowired
    private UserService userService;

    public Ticket buyTicket(String username, UUID courseId, List<UUID> places, UUID startStop, UUID endStop, double price) throws DomainViolation {
        return buyTicket(userService.getUser(username), courseId, places, startStop, endStop, price);
    }

    public Ticket buyTicket(UUID userid, UUID courseId, List<UUID> places, UUID startStop, UUID endStop, double price) throws DomainViolation {
        return buyTicket(entityService.getEntityById(userid, User.class), courseId, places, startStop, endStop, price);
    }

    public Ticket buyTicket(User user, UUID courseId, List<UUID> places, UUID startStop, UUID endStop, double price) throws DomainViolation {
        return createTicket(
                user,
                entityService.getEntityById(courseId, Course.class),
                new Distance(
                        entityService.getEntityById(startStop, Stop.class),
                        entityService.getEntityById(endStop, Stop.class)),
                places.stream().map(placeId -> entityService.getEntityById(placeId, Place.class)).toList(),
                price
                );
    }

    private Ticket createTicket(User user, Course course, Distance distance, List<Place> selectedPlaces, double price) throws DomainViolation{
        Distance newDistance = entityService.save(distance);
        Ticket newTicket = entityService.save(new Ticket(user, price, course, newDistance));
        selectedPlaces.forEach(place -> {
            entityService.save(new ReservedPlace(newTicket, place));
            entityService.save(new Availability(newDistance, place));
                });
        return newTicket;
    }

    public List<Ticket> getTicketsByUser(String username) {
        User user = userService.getUser(username);
        return getTicketsByUser(user);
    }

    private List<Ticket> getTicketsByUser(User user) {
        return user.getTickets();
    }
/*
    public Ticket getTicket(UUID id) throws DomainViolation {
        return entityService.getEntityById(id, Ticket.class);
    }

    public boolean isTicketForCourse(Ticket ticket, Course course) {
        return ticket.getCourse().equals(course);
    }*/
}
