package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {
    private final EntityService entityService;

    public Ticket createTicket(UUID userId, UUID courseId, List<UUID> places, UUID startStop, UUID endStop) throws DomainViolation {
        return createTicket(
                entityService.getEntityById(userId, User.class),
                entityService.getEntityById(courseId, Course.class),
                new Distance(
                        entityService.getEntityById(startStop, Stop.class),
                        entityService.getEntityById(endStop, Stop.class)),
                places.stream().map(placeId -> entityService.getEntityById(placeId, Place.class)).toList()
                );
    }

    private Ticket createTicket(User user, Course course, Distance distance, List<Place> selectedPlaces) throws DomainViolation{
        int price = distance.getTravelingTimeBetweenStops() / 10 * selectedPlaces.size();
        Ticket newTicket = entityService.save(new Ticket(user, price, course, distance));
        Distance newDistance = entityService.save(distance);
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
