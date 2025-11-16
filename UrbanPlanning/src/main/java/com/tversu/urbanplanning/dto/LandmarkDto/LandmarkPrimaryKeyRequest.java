package com.tversu.urbanplanning.dto.LandmarkDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LandmarkPrimaryKeyRequest {
    @NotBlank(message = "Название достопримечательности не может быть пустым")
    @Size(min = 2, max = 200)
    private String name;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String streetName;

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStreetName() {
        return streetName;
    }
}
