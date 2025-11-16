package com.tversu.urbanplanning.dto.ParticipationInCreationDto;

import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParticipationLandmarkRequest extends InfoOfCityAndStreetRequest {
    @NotBlank(message = "Название достопримечательности не может быть пустым")
    @Size(min = 2, max = 200)
    private String landmarkName;

    public String getLandmarkName() {
        return landmarkName;
    }
}
