package com.tversu.urbanplanning.dto.CreatorDto;

import com.tversu.urbanplanning.entity.Creator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatorResponseDto {
    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    private String fullName;

    public CreatorResponseDto() {
    }

    public CreatorResponseDto(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static CreatorResponseDto fromEntity(Creator creator) {
        return new CreatorResponseDto(creator.getFullName());
    }
}
