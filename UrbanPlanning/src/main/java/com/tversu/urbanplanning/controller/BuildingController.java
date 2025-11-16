package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.BuildingDto.*;
import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;
import com.tversu.urbanplanning.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/buildings")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponseDto>> getAllBuildings() {
        var buildings = buildingService.getAllBuildings();
        var dtos = buildings.stream()
                .map(BuildingResponseDto::fromEntity)
                .toList();
        return !dtos.isEmpty()
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<BuildingResponseDto> getBuildingByPrimaryKey(@RequestBody BuildingPrimaryKeyRequest request) {
        return buildingService.getBuildingByPrimaryKey(request.getHouseNumber(), request.getCityName(), request.getStreetName())
                .map(building -> ResponseEntity.ok(BuildingResponseDto.fromEntity(building)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-street")
    public ResponseEntity<List<BuildingResponseDto>> getBuildingsByStreet(@RequestBody InfoOfCityAndStreetRequest request) {
        try {
            var buildings = buildingService.getBuildingsByCityAndStreet(request.getStreetName(), request.getCityName());
            System.out.println(buildings.size());
            var dtos = buildings.stream()
                    .map(BuildingResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createBuilding(@RequestBody BuildingPrimaryKeyRequest request) {
        try {
            buildingService.createBuilding(request.getHouseNumber(), request.getCityName(), request.getStreetName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/house-number")
    public ResponseEntity<Void> updateBuildingHouseNumber(@RequestBody BuildingUpdateHouseNumberRequest request) {
        try {
            int result = buildingService.updateBuildingHouseNumber(
                    request.getOldHouseNumber(),
                    request.getNewHouseNumber(),
                    request.getCityName(),
                    request.getStreetName()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/street-only")
    public ResponseEntity<Void> updateBuildingStreetOnly(@RequestBody BuildingUpdateStreetOnlyRequest request) {
        try {
            int result = buildingService.updateBuildingStreetOnly(
                    request.getHouseNumber(),
                    request.getCityName(),
                    request.getOldStreetName(),
                    request.getNewStreetName()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/street-and-city")
    public ResponseEntity<Void> updateBuildingStreetAndCity(@RequestBody BuildingUpdateStreetAndCityRequest request) {
        try {
            int result = buildingService.updateBuildingStreetAndCity(
                    request.getHouseNumber(),
                    request.getOldCityName(),
                    request.getOldStreetName(),
                    request.getNewCityName(),
                    request.getNewStreetName()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody BuildingPrimaryKeyRequest request) {
        try {
            int result = buildingService.deleteBuilding(request.getHouseNumber(), request.getCityName(), request.getStreetName());
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}