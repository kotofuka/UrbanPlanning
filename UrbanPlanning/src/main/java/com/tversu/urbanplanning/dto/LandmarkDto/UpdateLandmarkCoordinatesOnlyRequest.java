package com.tversu.urbanplanning.dto.LandmarkDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateLandmarkCoordinatesOnlyRequest(
        @NotNull(message = "Широта обязательна")
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        BigDecimal latitude,

        @NotNull(message = "Долгота обязательна")
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        BigDecimal longitude
) {}
