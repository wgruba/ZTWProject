package org.example.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "City")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class City {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

}
