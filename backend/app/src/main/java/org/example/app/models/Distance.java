package org.example.app.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "distance", fetch = FetchType.LAZY)
    private List<Availability> availabilityList;

    @JsonIgnore
    @OneToMany(mappedBy = "distance", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Distance(Stop startStop, Stop endStop) {
        this.startStop = startStop;
        this.endStop = endStop;
    }

    public int getTravelingTimeBetweenStops() {
        return endStop.getTravellingTimeFromStart() - startStop.getTravellingTimeFromStart();
    }

    public boolean checkIfIntersect(Distance other) {
        if (!startStop.getRoute().equals(other.startStop.getRoute()))
            return false;

        return (startStop.isStopBefore(other.startStop) && other.startStop.isStopBefore(endStop)) ||
                (other.startStop.isStopBefore(startStop) && startStop.isStopBefore(other.endStop)) ||
                other.startStop.isTheSame(startStop) || other.endStop.isTheSame(endStop);
    }

}
