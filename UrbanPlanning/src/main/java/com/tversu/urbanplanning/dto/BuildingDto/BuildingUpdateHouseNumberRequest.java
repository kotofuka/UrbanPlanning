package com.tversu.urbanplanning.dto.BuildingDto;

import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;

public class BuildingUpdateHouseNumberRequest extends InfoOfCityAndStreetRequest {
    private String oldHouseNumber;
    private String newHouseNumber;


    public String getOldHouseNumber() {
        return oldHouseNumber;
    }

    public String getNewHouseNumber() {
        return newHouseNumber;
    }
}
