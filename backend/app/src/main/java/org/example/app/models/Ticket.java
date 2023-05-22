package org.example.app.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Ticket")
@NoArgsConstructor
@Setter
@Getter
public class Ticket {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "distance")
    private Distance distance;

    @JsonIgnore
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    private List<ReservedPlace> reservedPlaces;

    public Ticket(User user, double price, Course course, Distance distance) {
        this.user = user;
        this.price = price;
        this.course = course;
        this.distance = distance;
    }
}
