package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.exceptions.DomainViolation;
import org.example.app.models.City;
import org.example.app.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    @Autowired
    private final EntityService entityService;

    public List<City> getAllCities() throws DomainViolation {
        return entityService.getAll(City.class);
    }

    public City addCity(String name) throws DomainViolation {
        return entityService.save(new City(name));
    }
}
