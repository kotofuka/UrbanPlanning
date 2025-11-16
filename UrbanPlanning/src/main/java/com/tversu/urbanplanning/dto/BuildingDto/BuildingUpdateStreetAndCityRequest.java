package com.tversu.urbanplanning.dto.BuildingDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BuildingUpdateStreetAndCityRequest {
    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(min = 1, max = 20, message = "Номер дома должен быть от 1 до 20 символов")
    private String houseNumber;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String oldCityName;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String newCityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String oldStreetName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
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
