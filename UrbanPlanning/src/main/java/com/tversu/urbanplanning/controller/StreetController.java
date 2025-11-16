package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.InfoOfCityRequest;
import com.tversu.urbanplanning.dto.StreetDto.StreetPrimaryKeyRequest;
import com.tversu.urbanplanning.dto.StreetDto.StreetResponseDto;
import com.tversu.urbanplanning.dto.StreetDto.StreetUpdateRequest;
import com.tversu.urbanplanning.service.StreetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/streets")
public class StreetController {
    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public ResponseEntity<List<StreetResponseDto>> getAllStreets() {
        var streets = streetService.getAllStreets();
        var dtos = streets.stream()
                .map(StreetResponseDto::fromEntity)
                .toList();
        return !dtos.isEmpty()
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<StreetResponseDto> getStreetByNameAndCity(@RequestBody StreetPrimaryKeyRequest request) {
        return streetService.getStreetByNameAndCity(request.getName(), request.getCityName())
                .map(street -> ResponseEntity.ok(StreetResponseDto.fromEntity(street)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<StreetResponseDto>> getStreetsByCity(@RequestBody InfoOfCityRequest request) {
        try {
            var streets = streetService.getStreetsByCity(request.getCityName());
            var dtos = streets.stream()
                    .map(StreetResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createStreet(@RequestBody StreetPrimaryKeyRequest request) {
        try {
            streetService.createStreet(request.getName(), request.getCityName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateStreetName(@RequestBody StreetUpdateRequest request) {
        try {
            int result = streetService.updateStreetName(
                    request.getOldName(),
                    request.getNewName(),
                    request.getCityName()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStreet(@RequestBody StreetPrimaryKeyRequest request) {
        try {
            int result = streetService.deleteStreet(request.getName(), request.getCityName());
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
