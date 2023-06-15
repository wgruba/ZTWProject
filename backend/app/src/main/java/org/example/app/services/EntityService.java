package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.exceptions.Violations;
import org.example.app.models.*;
import org.example.app.repositories.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Map.entry;

@AllArgsConstructor
@Service
public class EntityService {
    public final AvailabilityRepository availabilityRepository;
    public final BusRepository busRepository;
    public final CityRepository cityRepository;
    public final CourseRepository courseRepository;
    public final DistanceRepository distanceRepository;
    public final PlaceRepository placeRepository;
    public final ReservedPlaceRepository reservedPlaceRepository;
    public final RouteRepository routeRepository;
    public final StopRepository stopRepository;
    public final TicketRepository ticketRepository;
    public final UserRepository userRepository;

    @SuppressWarnings("unchecked")
    private <Entity> JpaRepository<Entity, UUID> classToRepository(Class<?> entityClass) throws DomainViolation {
        Map<Class<?>, JpaRepository<?, UUID>> classToRepositoryMap = Map.ofEntries(
                entry(Availability.class, availabilityRepository),
                entry(Bus.class, busRepository),
                entry(City.class, cityRepository),
                entry(Course.class, courseRepository),
                entry(Distance.class, distanceRepository),
                entry(Place.class, placeRepository),
                entry(ReservedPlace.class, reservedPlaceRepository),
                entry(Route.class, routeRepository),
                entry(Stop.class, stopRepository),
                entry(Ticket.class, ticketRepository),
                entry(User.class, userRepository)
        );

        if (!classToRepositoryMap.containsKey(entityClass))
            Violations.entityClassNotFound.throwEx(entityClass.getSimpleName());
        return (JpaRepository<Entity, UUID>) classToRepositoryMap.get(entityClass);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public <Entity> Entity getEntityById(UUID id, Class<Entity> entityClass) throws DomainViolation {
        JpaRepository<Entity, UUID> repo = classToRepository(entityClass);
        Optional<Entity> maybeEntity = repo.findById(id);
        if (maybeEntity.isEmpty())
            Violations.entityNotFound.throwEx(entityClass.getSimpleName(), id.toString());
        return maybeEntity.get();
    }

    public <Entity> Entity save(Entity entity) throws DomainViolation {
        JpaRepository<Entity, UUID> repo = classToRepository(entity.getClass());
        return repo.save(entity);
    }

    public <Entity> List<Entity> getAll(Class<?> entityClass) throws  DomainViolation {
        JpaRepository<Entity, UUID> repo = classToRepository(entityClass);
        return repo.findAll();
    }
}
