package org.example.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "User")
@NoArgsConstructor
@Setter
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Ticket> tickets;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
