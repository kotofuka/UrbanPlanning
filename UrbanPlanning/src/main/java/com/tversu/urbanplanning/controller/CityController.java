package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<City>> getAllCities() {
        var cites = cityService.getAllCities();

        return !cites.isEmpty()
                ? ResponseEntity.ok(cites)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
