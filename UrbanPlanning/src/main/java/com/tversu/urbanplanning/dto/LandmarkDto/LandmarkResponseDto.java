package com.tversu.urbanplanning.dto.LandmarkDto;

import com.tversu.urbanplanning.entity.Landmark;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class LandmarkResponseDto {
    @NotBlank(message = "Название достопримечательности не может быть пустым")
    @Size(min = 2, max = 200)
    private String name;
    private String description;

    @NotNull(message = "Широта не может быть null")
    @DecimalMin(value = "-90", message = "Широта должна быть от -90 до 90")
    @DecimalMax(value = "90", message = "Широта должна быть от -90 до 90")
    private BigDecimal latitude;

    @NotNull(message = "Долгота не может быть null")
    @DecimalMin(value = "-180", message = "Долгота должна быть от -180 до 180")
    @DecimalMax(value = "180", message = "Долгота должна быть от -180 до 180")
    private BigDecimal longitude;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    private String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String streetName;

    public LandmarkResponseDto() {}

    public LandmarkResponseDto(String name, String description, BigDecimal latitude,
                               BigDecimal longitude, String cityName, String streetName) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
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

    public static LandmarkResponseDto fromEntity(Landmark landmark) {
        return new LandmarkResponseDto(
                landmark.getId().getName(),
                landmark.getDescription(),
                landmark.getLatitude(),
                landmark.getLongitude(),
                landmark.getId().getCityName(),
                landmark.getId().getStreetName()
        );
    }
}
