package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;
import com.tversu.urbanplanning.dto.LandmarkDto.*;
import com.tversu.urbanplanning.dto.InfoOfCityRequest;
import com.tversu.urbanplanning.service.LandmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/landmarks")
public class LandmarkController {
    private final LandmarkService landmarkService;

    public LandmarkController(LandmarkService landmarkService) {
        this.landmarkService = landmarkService;
    }

    @GetMapping
    public ResponseEntity<List<LandmarkResponseDto>> getAllLandmarks() {
        var landmarks = landmarkService.getAllLandmarks();
        var dtos = landmarks.stream()
                .map(LandmarkResponseDto::fromEntity)
                .toList();
        return !dtos.isEmpty()
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<LandmarkResponseDto> getLandmarkByKey(@RequestBody LandmarkPrimaryKeyRequest request) {
        return landmarkService.getLandmarkByKey(request.getName(), request.getCityName(), request.getStreetName())
                .map(landmark -> ResponseEntity.ok(LandmarkResponseDto.fromEntity(landmark)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-city")
    public ResponseEntity<List<LandmarkResponseDto>> getLandmarksByCity(@RequestBody InfoOfCityRequest request) {
        var landmarks = landmarkService.getLandmarksByCity(request.getCityName());
        var dtos = landmarks.stream()
                .map(LandmarkResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-street")
    public ResponseEntity<List<LandmarkResponseDto>> getLandmarksByStreet(@RequestBody InfoOfCityAndStreetRequest request) {
        try {
            var landmarks = landmarkService.getLandmarksByStreet(request.getStreetName(), request.getCityName());
            var dtos = landmarks.stream()
                    .map(LandmarkResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createLandmark(@RequestBody AllDataLandmarkRequest request) {
        try {
            landmarkService.createLandmark(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/description")
    public ResponseEntity<Void> updateLandmarkDescription(@RequestBody LandmarkDescriptionUpdateRequest request) {
        try {
            int result = landmarkService.updateLandmarkDescription(
                    request.getName(),
                    request.getCityName(),
                    request.getStreetName(),
                    request.getDescription()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/coordinates")
    public ResponseEntity<Void> updateLandmarkCoordinates(@RequestBody LandmarkCoordinatesUpdateRequest request) {
        try {
            UpdateLandmarkCoordinatesOnlyRequest coordRequest = new UpdateLandmarkCoordinatesOnlyRequest(
                    request.getLatitude(),
                    request.getLongitude()
            );
            int result = landmarkService.updateLandmarkCoordinates(
                    request.getName(),
                    request.getCityName(),
                    request.getStreetName(),
                    coordRequest
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/latitude")
    public ResponseEntity<Void> updateLandmarkLatitude(@RequestBody LandmarkLatitudeUpdateRequest request) {
        try {
            int result = landmarkService.updateLandmarkLatitude(
                    request.getName(),
                    request.getCityName(),
                    request.getStreetName(),
                    request.getLatitude()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/longitude")
    public ResponseEntity<Void> updateLandmarkLongitude(@RequestBody LandmarkLongitudeUpdateRequest request) {
        try {
            int result = landmarkService.updateLandmarkLongitude(
                    request.getName(),
                    request.getCityName(),
                    request.getStreetName(),
                    request.getLongitude()
            );
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/additional-data")
    public ResponseEntity<Void> updateLandmarkAdditionalData(@RequestBody AllDataLandmarkRequest request) {
        try {
            int result = landmarkService.updateLandmarkAdditionalData(request);
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLandmark(@RequestBody LandmarkPrimaryKeyRequest request) {
        try {
            int result = landmarkService.deleteLandmark(request.getName(), request.getCityName(), request.getStreetName());
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
