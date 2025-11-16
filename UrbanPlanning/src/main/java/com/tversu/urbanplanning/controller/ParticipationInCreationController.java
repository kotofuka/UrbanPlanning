package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.InfoOfCreatorRequest;
import com.tversu.urbanplanning.dto.ParticipationInCreationDto.ParticipationLandmarkRequest;
import com.tversu.urbanplanning.dto.ParticipationInCreationDto.ParticipationPrimaryKeyRequest;
import com.tversu.urbanplanning.dto.ParticipationInCreationDto.ParticipationResponseDto;
import com.tversu.urbanplanning.service.ParticipationInCreationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/participations")
public class ParticipationInCreationController {
    private final ParticipationInCreationService participationInCreationService;

    public ParticipationInCreationController(ParticipationInCreationService participationInCreationService) {
        this.participationInCreationService = participationInCreationService;
    }

    @GetMapping
    public ResponseEntity<List<ParticipationResponseDto>> getAllParticipations() {
        var participations = participationInCreationService.getAllParticipations();
        var dtos = participations.stream()
                .map(ParticipationResponseDto::fromEntity)
                .toList();
        return !dtos.isEmpty()
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ParticipationResponseDto> getParticipationByKey(@RequestBody @Valid ParticipationPrimaryKeyRequest request) {
        return participationInCreationService.getParticipationByKey(
                        request.getLandmarkName(),
                        request.getCityName(),
                        request.getStreetName(),
                        request.getCreatorFullName())
                .map(participation -> ResponseEntity.ok(ParticipationResponseDto.fromEntity(participation)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-landmark")
    public ResponseEntity<List<ParticipationResponseDto>> getParticipationsByLandmark(@RequestBody @Valid ParticipationLandmarkRequest request) {
        try {
            var participations = participationInCreationService.getParticipationsByLandmark(
                    request.getLandmarkName(),
                    request.getCityName(),
                    request.getStreetName());
            var dtos = participations.stream()
                    .map(ParticipationResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/by-creator")
    public ResponseEntity<List<ParticipationResponseDto>> getParticipationsByCreator(@RequestBody @Valid InfoOfCreatorRequest request) {
        try {
            var participations = participationInCreationService.getParticipationsByCreator(request.getCreatorFullName());
            var dtos = participations.stream()
                    .map(ParticipationResponseDto::fromEntity)
                    .toList();
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createParticipation(@RequestBody @Valid ParticipationPrimaryKeyRequest request) {
        try {
            participationInCreationService.createParticipation(
                    request.getLandmarkName(),
                    request.getCityName(),
                    request.getStreetName(),
                    request.getCreatorFullName()
            );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteParticipation(@RequestBody @Valid ParticipationPrimaryKeyRequest request) {
        try {
            int result = participationInCreationService.deleteParticipation(
                    request.getLandmarkName(),
                    request.getCityName(),
                    request.getStreetName(),
                    request.getCreatorFullName()
            );
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/by-landmark")
    public ResponseEntity<Void> deleteParticipationsByLandmark(@RequestBody @Valid ParticipationLandmarkRequest request) {
        try {
            int result = participationInCreationService.deleteParticipationsByLandmark(
                    request.getLandmarkName(),
                    request.getCityName(),
                    request.getStreetName()
            );
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}