package com.tversu.urbanplanning.dto.BuildingDto;

import com.tversu.urbanplanning.entity.Building;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BuildingResponseDto {
    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(min = 1, max = 20, message = "Номер дома должен быть от 1 до 20 символов")
    private String houseNumber;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String streetName;

    public BuildingResponseDto() {}

    public BuildingResponseDto(String houseNumber, String cityName, String streetName) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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

    public static BuildingResponseDto fromEntity(Building building) {
        return new BuildingResponseDto(
                building.getId().getHouseNumber(),
                building.getId().getCityName(),
                building.getId().getStreetName()
        );
    }
}