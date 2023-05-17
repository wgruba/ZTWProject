package org.example.app.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Course")
@NoArgsConstructor
@Setter
@Getter
public class Course {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "courseDate")
    private LocalDateTime courseDate;

    @ManyToOne
    @JoinColumn(name = "bus")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route")
    private Route route;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Place> places;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public Course(LocalDateTime courseDate, Bus bus, Route route) {
        this.courseDate = courseDate;
        this.bus = bus;
        this.route = route;
    }
}
