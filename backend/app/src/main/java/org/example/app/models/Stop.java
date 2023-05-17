package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Stop")
@NoArgsConstructor
@Setter
@Getter
public class Stop {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "travellingTimeFromStart")
    private int travellingTimeFromStart;

    @ManyToOne
    @JoinColumn(name = "route")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    public Stop(int travellingTimeFromStart, Route route, City city) {
        this.travellingTimeFromStart = travellingTimeFromStart;
        this.route = route;
        this.city = city;
    }
}
