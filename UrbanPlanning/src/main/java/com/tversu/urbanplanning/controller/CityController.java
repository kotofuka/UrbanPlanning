package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.CityDto;
import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getAllCities() {
        var cites = cityService.getAllCities();

        System.out.println(cites.getFirst());

        return !cites.isEmpty()
                ? ResponseEntity.ok(cites.stream().map(City::toString).toList())
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
