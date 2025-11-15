package com.tversu.urbanplanning.dto.LandmarkDto;

import java.math.BigDecimal;

public record LandmarkResponse(
        String name,
        String description,
        BigDecimal latitude,
        BigDecimal longitude,
        String cityName,
        String streetName) {
}
