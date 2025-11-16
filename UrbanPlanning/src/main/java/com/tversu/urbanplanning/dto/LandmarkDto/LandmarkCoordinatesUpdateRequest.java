package com.tversu.urbanplanning.dto.LandmarkDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LandmarkCoordinatesUpdateRequest extends LandmarkLatitudeUpdateRequest{
    @NotNull(message = "Долгота не может быть null")
    @DecimalMin(value = "-180", message = "Долгота должна быть от -180 до 180")
    @DecimalMax(value = "180", message = "Долгота должна быть от -180 до 180")
    private BigDecimal longitude;

    public BigDecimal getLongitude() {
        return longitude;
    }
}
