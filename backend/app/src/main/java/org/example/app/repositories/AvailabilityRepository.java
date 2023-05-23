package org.example.app.repositories;

import org.example.app.models.Availability;
import org.example.app.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
    List<Availability> findAllByPlace(Place place);
}
