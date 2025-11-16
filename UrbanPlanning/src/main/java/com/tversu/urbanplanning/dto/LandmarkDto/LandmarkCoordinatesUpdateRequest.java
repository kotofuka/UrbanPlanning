package com.tversu.urbanplanning.dto.LandmarkDto;

import java.math.BigDecimal;

public class LandmarkCoordinatesUpdateRequest extends LandmarkLatitudeUpdateRequest{
    private BigDecimal longitude;

    public BigDecimal getLongitude() {
        return longitude;
    }
}
