package org.example.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Bus")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Bus {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;
}
