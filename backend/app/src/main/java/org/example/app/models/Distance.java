package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Distance")
@NoArgsConstructor
@Setter
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

    public Distance(Stop startStop, Stop endStop) {
        this.startStop = startStop;
        this.endStop = endStop;
    }

    public int getTravelingTimeBetweenStops() {
        return endStop.getTravellingTimeFromStart() - startStop.getTravellingTimeFromStart();
    }

}
