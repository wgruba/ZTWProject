package org.example.app.repositories;

import org.example.app.models.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, UUID> {
}
