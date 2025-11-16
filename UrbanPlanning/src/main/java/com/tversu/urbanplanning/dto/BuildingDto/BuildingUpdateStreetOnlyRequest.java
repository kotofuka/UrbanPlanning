package com.tversu.urbanplanning.dto.BuildingDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BuildingUpdateStreetOnlyRequest {
    private String houseNumber;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String oldStreetName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
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
