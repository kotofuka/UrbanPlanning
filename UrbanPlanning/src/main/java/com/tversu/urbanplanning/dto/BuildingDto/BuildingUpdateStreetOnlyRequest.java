package com.tversu.urbanplanning.dto.BuildingDto;

public class BuildingUpdateStreetOnlyRequest {
    private String houseNumber;
    private String cityName;
    private String oldStreetName;
    private String newStreetName;

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public String getOldStreetName() {
        return oldStreetName;
    }

    public String getNewStreetName() {
        return newStreetName;
    }
}
