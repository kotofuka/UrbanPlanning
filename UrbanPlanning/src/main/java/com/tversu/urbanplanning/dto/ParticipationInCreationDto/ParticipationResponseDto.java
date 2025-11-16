package com.tversu.urbanplanning.dto.ParticipationInCreationDto;

import com.tversu.urbanplanning.entity.ParticipationInCreation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParticipationResponseDto {
    @NotBlank(message = "Название достопримечательности не может быть пустым")
    @Size(min = 2, max = 200)
    private String landmarkName;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String streetName;

    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    private String creatorFullName;

    public ParticipationResponseDto() {}

    public ParticipationResponseDto(String landmarkName, String cityName, String streetName, String getCreatorFullName) {
        this.landmarkName = landmarkName;
        this.cityName = cityName;
        this.streetName = streetName;
        this.creatorFullName = getCreatorFullName;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public static ParticipationResponseDto fromEntity(ParticipationInCreation participation) {
        return new ParticipationResponseDto(
                participation.getId().getLandmarkName(),
                participation.getId().getCityName(),
                participation.getId().getStreetName(),
                participation.getId().getCreatorFullName()
        );
    }
}