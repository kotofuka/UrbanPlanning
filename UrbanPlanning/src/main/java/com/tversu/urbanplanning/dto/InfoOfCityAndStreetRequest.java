package com.tversu.urbanplanning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InfoOfCityAndStreetRequest {
    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String streetName;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }
}
