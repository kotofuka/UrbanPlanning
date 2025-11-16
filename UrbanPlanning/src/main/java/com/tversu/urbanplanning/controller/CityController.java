package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.CityDto.CityPrimaryKeyRequest;
import com.tversu.urbanplanning.dto.CityDto.CityUpdateRequest;
import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.service.CityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        var cities = cityService.getAllCities();
        return !cities.isEmpty()
                ?ResponseEntity.ok(cities)
                :ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<City> getCityByName(@RequestBody @Valid CityPrimaryKeyRequest request)
    {
        return cityService.getCityByName(request.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCity(@RequestBody @Valid CityPrimaryKeyRequest request) {
        try{
            cityService.createCity(request.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateCity(@RequestBody @Valid CityUpdateRequest request) {
        try{
            int result = cityService.updateCity(request.getOldName(), request.getNewName());
            return result > 0
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCity(@RequestBody @Valid CityPrimaryKeyRequest request) {
        try {
            int result = cityService.deleteCity(request.getName());
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
