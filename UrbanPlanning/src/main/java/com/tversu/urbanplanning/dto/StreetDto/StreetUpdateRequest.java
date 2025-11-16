package com.tversu.urbanplanning.dto.StreetDto;

public class StreetUpdateRequest {
    private String oldName;
    private String newName;
    private String cityName;

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public String getCityName() {
        return cityName;
    }
}
