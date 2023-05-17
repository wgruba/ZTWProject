package org.example.app.repositories;

import org.example.app.models.ReservedPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservedPlaceRepository extends JpaRepository<ReservedPlace, UUID> {
}
