package com.tversu.urbanplanning.dto.BuildingDto;

import com.tversu.urbanplanning.dto.InfoOfCityAndStreetRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BuildingUpdateHouseNumberRequest extends InfoOfCityAndStreetRequest {
    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(min = 1, max = 20, message = "Номер дома должен быть от 1 до 20 символов")
    private String oldHouseNumber;

    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(min = 1, max = 20, message = "Номер дома должен быть от 1 до 20 символов")
    private String newHouseNumber;


    public String getOldHouseNumber() {
        return oldHouseNumber;
    }

    public String getNewHouseNumber() {
        return newHouseNumber;
    }
}
