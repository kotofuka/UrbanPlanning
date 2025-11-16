package com.tversu.urbanplanning.dto.LandmarkDto;

import com.tversu.urbanplanning.entity.Landmark;

import java.math.BigDecimal;

public class LandmarkResponseDto {
    private String name;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String cityName;
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
