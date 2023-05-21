package org.example.app.models;


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

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<Availability> availabilityList;

    public Place(Course course) {
        this.course = course;
    }
}
