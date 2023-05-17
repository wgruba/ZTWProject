package org.example.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ReservedPlaces")
@NoArgsConstructor
@Setter
@Getter
public class ReservedPlace {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ticket")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "place")
    private Place place;

    public ReservedPlace(Ticket ticket, Place place) {
        this.ticket = ticket;
        this.place = place;
    }
}
