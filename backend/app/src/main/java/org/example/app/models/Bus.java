package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Bus")
@NoArgsConstructor
@Setter
@Getter
public class Bus {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "type")
    private BusType type;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<Course> courses;

    public enum BusType {
        Long,
        Short,
    }

    public Bus(BusType type) {
        this.type = type;
    }
}
