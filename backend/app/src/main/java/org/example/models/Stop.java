package org.example.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Stop")
@NoArgsConstructor
@AllArgsConstructor
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
}
