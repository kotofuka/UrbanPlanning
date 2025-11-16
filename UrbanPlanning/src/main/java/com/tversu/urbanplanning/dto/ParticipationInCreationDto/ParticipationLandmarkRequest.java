package com.tversu.urbanplanning.dto.ParticipationInCreationDto;

import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;

public class ParticipationLandmarkRequest extends InfoOfCityAndStreetRequest {
    private String landmarkName;

    public String getLandmarkName() {
        return landmarkName;
    }
}
