package com.tversu.urbanplanning.dto.BuildingDto;

public class BuildingUpdateStreetAndCityRequest {
    private String houseNumber;
    private String oldCityName;
    private String newCityName;
    private String oldStreetName;
    private String newStreetName;

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getOldCityName() {
        return oldCityName;
    }

    public String getNewCityName() {
        return newCityName;
    }

    public String getOldStreetName() {
        return oldStreetName;
    }

    public String getNewStreetName() {
        return newStreetName;
    }
}
