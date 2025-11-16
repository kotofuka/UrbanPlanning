package com.tversu.urbanplanning.dto.ParticipationInCreationDto;

import com.tversu.urbanplanning.entity.ParticipationInCreation;

public class ParticipationResponseDto {
    private String landmarkName;
    private String cityName;
    private String streetName;
    private String creatorFullName;

    public ParticipationResponseDto() {}

    public ParticipationResponseDto(String landmarkName, String cityName, String streetName, String getCreatorFullName) {
        this.landmarkName = landmarkName;
        this.cityName = cityName;
        this.streetName = streetName;
        this.creatorFullName = getCreatorFullName;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public static ParticipationResponseDto fromEntity(ParticipationInCreation participation) {
        return new ParticipationResponseDto(
                participation.getId().getLandmarkName(),
                participation.getId().getCityName(),
                participation.getId().getStreetName(),
                participation.getId().getCreatorFullName()
        );
    }
}