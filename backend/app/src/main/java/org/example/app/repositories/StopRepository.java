package org.example.app.repositories;

import org.example.app.models.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<Stop, UUID> {
}
