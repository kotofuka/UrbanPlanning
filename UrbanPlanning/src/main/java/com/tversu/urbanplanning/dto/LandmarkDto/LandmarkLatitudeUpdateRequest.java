package com.tversu.urbanplanning.dto.LandmarkDto;

import java.math.BigDecimal;

public class LandmarkLatitudeUpdateRequest extends LandmarkPrimaryKeyRequest{
    private BigDecimal latitude;

    public BigDecimal getLatitude() {
        return latitude;
    }
}
