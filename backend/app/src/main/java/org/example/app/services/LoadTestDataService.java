package org.example.app.services;

import org.example.app.exceptions.DomainViolation;
import org.example.app.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class LoadTestDataService {
    @Autowired
    private EntityService entityService;
    public void loadTestData() {
        Bus longBus = entityService.save(new Bus(Bus.BusType.Long));
        Bus shortBus = entityService.save(new Bus(Bus.BusType.Short));

        City poznan = entityService.save(new City("Poznan"));
        City warszawa = entityService.save(new City("Warszawa"));
        City wroclaw = entityService.save(new City("Wroclaw"));
        City krakow = entityService.save(new City("Krakow"));

        Route warszawa_krakow = entityService.save(new Route("Warszawa_Krakow"));
        Route krakow_warszawa = entityService.save(new Route("Krakow_Warszawa"));

        Stop warszawa0 = entityService.save(new Stop(0, warszawa_krakow, warszawa));
        Stop poznan0 = entityService.save(new Stop(240, warszawa_krakow, poznan));
        Stop wroclaw0 = entityService.save(new Stop(360, warszawa_krakow, wroclaw));
        Stop krakow0 = entityService.save(new Stop(480, warszawa_krakow, krakow));

        Stop warszawa1 = entityService.save(new Stop(480, krakow_warszawa, warszawa));
        Stop poznan1 = entityService.save(new Stop(360, krakow_warszawa, poznan));
        Stop wroclaw1 = entityService.save(new Stop(240, krakow_warszawa, wroclaw));
        Stop krakow1 = entityService.save(new Stop(0, krakow_warszawa, krakow));

        Course[] course_wk = {
                entityService.save(new Course(LocalDateTime.of(2023, 6, 1, 12, 0), longBus, warszawa_krakow)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 2, 12, 0), longBus, warszawa_krakow)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 3, 12, 0), longBus, warszawa_krakow)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 4, 12, 0), longBus, warszawa_krakow))};

        Course[] course_kw = {
                entityService.save(new Course(LocalDateTime.of(2023, 6, 1, 12, 0), shortBus, krakow_warszawa)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 2, 12, 0), shortBus, krakow_warszawa)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 3, 12, 0), shortBus, krakow_warszawa)),
                entityService.save(new Course(LocalDateTime.of(2023, 6, 4, 12, 0), shortBus, krakow_warszawa))};

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 40; j++) {
                entityService.save(new Place(course_wk[i]));
            }
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 20; j++) {
                entityService.save(new Place(course_kw[i]));
            }
        }
    }
}
