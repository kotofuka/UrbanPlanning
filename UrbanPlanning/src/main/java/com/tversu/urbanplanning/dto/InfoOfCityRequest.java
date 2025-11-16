package com.tversu.urbanplanning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InfoOfCityRequest {
    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    public String getCityName() {
        return cityName;
    }
}
