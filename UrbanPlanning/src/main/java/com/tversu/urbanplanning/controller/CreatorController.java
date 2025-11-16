package com.tversu.urbanplanning.controller;

import com.tversu.urbanplanning.dto.CreatorDto.CreatorPrimaryKeyRequest;
import com.tversu.urbanplanning.dto.CreatorDto.CreatorResponseDto;
import com.tversu.urbanplanning.dto.CreatorDto.CreatorUpdateRequest;
import com.tversu.urbanplanning.service.CreatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_planning/admin_panel/creators")
public class CreatorController {
    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping
    public ResponseEntity<List<CreatorResponseDto>> getAllCreators() {
        var creators = creatorService.getAllCreators();
        var dtos = creators.stream()
                .map(CreatorResponseDto::fromEntity)
                .toList();
        return !dtos.isEmpty()
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<CreatorResponseDto> getCreatorByName(@RequestBody CreatorPrimaryKeyRequest request) {
        return creatorService.getCreatorByName(request.getFullName())
                .map(creator -> ResponseEntity.ok(CreatorResponseDto.fromEntity(creator)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCreator(@RequestBody CreatorPrimaryKeyRequest request) {
        try {
            creatorService.createCreator(request.getFullName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateCreator(@RequestBody CreatorUpdateRequest request) {
        try {
            int result = creatorService.updateCreator(request.getOldFullName(), request.getNewFullName());
            return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCreator(@RequestBody CreatorPrimaryKeyRequest request) {
        try {
            int result = creatorService.deleteCreator(request.getFullName());
            return result > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}