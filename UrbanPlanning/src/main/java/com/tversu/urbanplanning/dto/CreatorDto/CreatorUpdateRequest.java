package com.tversu.urbanplanning.dto.CreatorDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatorUpdateRequest {
    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    private String oldFullName;

    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    private String newFullName;

    public String getOldFullName() {
        return oldFullName;
    }

    public String getNewFullName() {
        return newFullName;
    }
}
