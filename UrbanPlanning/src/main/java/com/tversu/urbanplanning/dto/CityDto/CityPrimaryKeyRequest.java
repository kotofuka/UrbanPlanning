package com.tversu.urbanplanning.dto.CityDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CityPrimaryKeyRequest {
    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String name;

    public String getName() {
        return name;
    }
}
