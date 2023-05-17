package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.models.*;
import org.example.app.repositories.ReservedPlaceRepository;
import org.example.app.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ReservedPlaceRepository reservedPlaceRepository;

    public Ticket createTicket(User user, Course course, Distance distance, List<Place> selectedPlaces) {
        int price = distance.getTravelingTimeBetweenStops() / 10 * selectedPlaces.size();
        Ticket newTicket = ticketRepository.save(new Ticket(user, price, course, distance));
        selectedPlaces.forEach(place -> reservedPlaceRepository.save(new ReservedPlace(newTicket, place)));
        return newTicket;
    }
}
