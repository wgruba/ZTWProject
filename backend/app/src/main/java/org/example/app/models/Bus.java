package org.example.app.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Bus", schema = "ztw")
@NoArgsConstructor
@Setter
@Getter
public class Bus {
    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "gene")
    private UUID id;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<Course> courses;

    public enum BusType {
        Long("Long"),
        Short("Short");

        private final String value;

        private BusType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public Bus(BusType type) {
        this.type = type.getValue();
    }
}
