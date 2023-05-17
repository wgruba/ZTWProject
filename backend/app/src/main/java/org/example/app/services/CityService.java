package org.example.app.services;

import lombok.AllArgsConstructor;
import org.example.app.models.City;
import org.example.app.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
