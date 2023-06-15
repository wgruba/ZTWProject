package org.example.app.repositories;

import org.example.app.models.Course;
import org.example.app.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
    List<Place> findAllByCourse(Course course);
}
