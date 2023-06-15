package org.example.app.controllers.responses.ticket;

import lombok.Getter;
import org.example.app.models.Place;
import org.example.app.models.ReservedPlace;
import org.example.app.models.Ticket;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TicketInfo {
    private final String routeName;
    private final String startCityName;
    private final String endCityName;
    private final double price;
    private final LocalDateTime departureTime;
    private final String qrCode;

    public TicketInfo(Ticket ticket) {
        this.routeName = ticket.getCourse().getRoute().getName();
        this.startCityName = ticket.getDistance().getStartStop().getCity().getName();
        this.endCityName = ticket.getDistance().getEndStop().getCity().getName();
        this.price = ticket.getPrice();
        this.departureTime = ticket.getCourse().getCourseDate().plusMinutes(
                ticket.getDistance().getStartStop().getTravellingTimeFromStart()
        );
        this.qrCode = "FROM: " + this.startCityName + " TO: " + this.endCityName + "AT: " + this.departureTime +
                " PLACES: " + placesToString(ticket.getReservedPlaces());
    }

    private String placesToString(List<ReservedPlace> places) {
        StringBuilder val = new StringBuilder();
        for (int nr : places.stream().map(ReservedPlace::getPlace).map(Place::getNr).toList()) {
            val.append(", ").append(nr);
        }
        return val.toString();
    }
}
