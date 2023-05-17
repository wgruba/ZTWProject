package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
