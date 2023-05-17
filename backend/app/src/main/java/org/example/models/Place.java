package org.example.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Place")
@NoArgsConstructor
@AllArgsConstructor
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
}
