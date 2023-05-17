package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Availability")
@NoArgsConstructor
@Getter
@Setter
public class Availability {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "distance")
    private Distance distance;

    @ManyToOne
    @JoinColumn(name = "place")
    private Place place;

    public Availability(Distance distance, Place place) {
        this.distance = distance;
        this.place = place;
    }
}
