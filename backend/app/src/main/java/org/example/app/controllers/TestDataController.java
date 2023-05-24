package org.example.app.controllers;

import org.example.app.services.LoadTestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("unused")
public class TestDataController {
    @Autowired
    private LoadTestDataService testDataService;

    @RequestMapping("/load")
    public ResponseEntity<?> loadTestData() {
        testDataService.loadTestData();
        return ResponseEntity.ok().build();
    }
}
