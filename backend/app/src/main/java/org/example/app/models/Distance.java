package org.example.app.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Distance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Distance {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "startStop")
    private Stop startStop;

    @ManyToOne
    @JoinColumn(name = "endStop")
    private Stop endStop;

    @OneToMany(mappedBy = "distance", fetch = FetchType.LAZY)
    private List<Availability> availabilityList;

    @OneToMany(mappedBy = "distance", fetch = FetchType.LAZY)
    private List<Ticket> tickets;



}
