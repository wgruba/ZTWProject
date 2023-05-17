package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "City")
@NoArgsConstructor
@Setter
@Getter
public class City {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Stop> stops;

    public City(String name) {
        this.name = name;
    }
}
