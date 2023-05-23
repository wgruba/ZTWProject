package org.example.app.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Route")
@NoArgsConstructor
@Setter
@Getter
public class Route {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<Stop> stops;

    public Route(String name) {
        this.name = name;
    }

    public List<Stop> orderedStops() {
        return stops.stream().sorted(Comparator.comparingInt(Stop::getTravellingTimeFromStart)).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
