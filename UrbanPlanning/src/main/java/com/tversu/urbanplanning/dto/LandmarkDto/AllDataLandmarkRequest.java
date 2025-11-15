package com.tversu.urbanplanning.dto.LandmarkDto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AllDataLandmarkRequest(
        @NotBlank(message = "Название достопримечательности не может быть пустым")
        @Size(min = 2, max = 200)
        String name,

        @Size(max = 5000)
        String description,

        @NotNull(message = "Широта обязательна")
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        BigDecimal latitude,

        @NotNull(message = "Долгота обязательна")
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        BigDecimal longitude,

        @NotBlank(message = "Город обязателен")
        String cityName,

        @NotBlank(message = "Улица обязательна")
        String streetName
) {}
