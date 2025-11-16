package com.tversu.urbanplanning.dto.StreetDto;

import com.tversu.urbanplanning.entity.Street;

public class StreetResponseDto {
    private String name;
    private String cityName;

    public StreetResponseDto() {}

    public StreetResponseDto(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public static StreetResponseDto fromEntity(Street street) {
        return new StreetResponseDto(
                street.getId().getName(),
                street.getId().getCityName()
        );
    }
}
