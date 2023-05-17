package org.example.app.repositories;

import org.example.app.models.City;
import org.example.app.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<Stop, UUID> {
    List<Stop> findAllByCity(City city);
}
