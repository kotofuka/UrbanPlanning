package com.tversu.urbanplanning.dto.ParticipationInCreationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParticipationPrimaryKeyRequest extends ParticipationLandmarkRequest{
    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    private String creatorFullName;

    public String getCreatorFullName() {
        return creatorFullName;
    }
}
