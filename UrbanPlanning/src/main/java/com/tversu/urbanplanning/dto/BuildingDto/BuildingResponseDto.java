package com.tversu.urbanplanning.dto.BuildingDto;

import com.tversu.urbanplanning.entity.Building;

public class BuildingResponseDto {
    private String houseNumber;
    private String cityName;
    private String streetName;

    public BuildingResponseDto() {}

    public BuildingResponseDto(String houseNumber, String cityName, String streetName) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public static BuildingResponseDto fromEntity(Building building) {
        return new BuildingResponseDto(
                building.getId().getHouseNumber(),
                building.getId().getCityName(),
                building.getId().getStreetName()
        );
    }
}