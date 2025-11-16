package com.tversu.urbanplanning.dto.StreetDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StreetUpdateRequest {
    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String oldName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String newName;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
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
