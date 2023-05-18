package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.exceptions.Violations;
import org.example.app.models.*;
import org.example.app.repositories.AvailabilityRepository;
import org.example.app.repositories.DistanceRepository;
import org.example.app.repositories.ReservedPlaceRepository;
import org.example.app.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ReservedPlaceRepository reservedPlaceRepository;
    private final DistanceRepository distanceRepository;
    private final AvailabilityRepository availabilityRepository;

    public Ticket createTicket(User user, Course course, Distance distance, List<Place> selectedPlaces) {
        int price = distance.getTravelingTimeBetweenStops() / 10 * selectedPlaces.size();
        Ticket newTicket = ticketRepository.save(new Ticket(user, price, course, distance));
        Distance newDistance = distanceRepository.save(distance);
        selectedPlaces.forEach(place -> {
            reservedPlaceRepository.save(new ReservedPlace(newTicket, place));
            availabilityRepository.save(new Availability(newDistance, place));
                });
        return newTicket;
    }

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    public Ticket getTicket(UUID id) throws DomainViolation {
        Optional<Ticket> maybeTicket = ticketRepository.findById(id);
        if (maybeTicket.isEmpty())
            Violations.entityNotFound.throwEx("Ticket", id.toString());
        return maybeTicket.get();
    }

    public boolean isTicketForCourse(Ticket ticket, Course course) {
        return ticket.getCourse().equals(course);
    }
}
