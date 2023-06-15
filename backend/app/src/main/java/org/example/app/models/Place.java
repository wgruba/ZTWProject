package org.example.app.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Place")
@NoArgsConstructor
@Setter
@Getter
public class Place {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nr")
    private int nr;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<Availability> availabilityList;

    public Place(Course course, int nr) {
        this.course = course;
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Place(" + nr + ", " + id.toString() + ")";
    }
}
