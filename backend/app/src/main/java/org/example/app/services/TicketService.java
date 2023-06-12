package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {
    @Autowired
    private EntityService entityService;

    public Ticket buyTicket(Optional<UUID> userId, UUID courseId, List<UUID> places, UUID startStop, UUID endStop, double price) throws DomainViolation {
        return createTicket(userId.orElse(getDummyUser()), courseId, places, startStop, endStop, price);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private UUID getDummyUser() {
        return entityService.userRepository.findAllByUsername("dummy").stream().findFirst().get().getId();
    }

    private Ticket createTicket(UUID userId, UUID courseId, List<UUID> places, UUID startStop, UUID endStop, double price) throws DomainViolation {
        return createTicket(
                entityService.getEntityById(userId, User.class),
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

    public List<Ticket> getTicketsByUser(User user) {
        return entityService.ticketRepository.findAllByUser(user);
    }

    public Ticket getTicket(UUID id) throws DomainViolation {
        return entityService.getEntityById(id, Ticket.class);
    }

    public boolean isTicketForCourse(Ticket ticket, Course course) {
        return ticket.getCourse().equals(course);
    }
}
