package com.tversu.urbanplanning.dto.LandmarkDto;

import java.math.BigDecimal;

public class LandmarkLongitudeUpdateRequest extends LandmarkPrimaryKeyRequest{
    private BigDecimal longitude;

    public BigDecimal getLongitude() {
        return longitude;
    }
}
