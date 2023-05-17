package org.example.app.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Availability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
