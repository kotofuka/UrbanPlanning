package com.tversu.urbanplanning.dto.LandmarkDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LandmarkLatitudeUpdateRequest extends LandmarkPrimaryKeyRequest{
    @NotNull(message = "Широта не может быть null")
    @DecimalMin(value = "-90", message = "Широта должна быть от -90 до 90")
    @DecimalMax(value = "90", message = "Широта должна быть от -90 до 90")
    private BigDecimal latitude;

    public BigDecimal getLatitude() {
        return latitude;
    }
}
