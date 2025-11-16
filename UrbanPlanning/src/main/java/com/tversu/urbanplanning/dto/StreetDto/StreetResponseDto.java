package com.tversu.urbanplanning.dto.StreetDto;

import com.tversu.urbanplanning.entity.Street;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StreetResponseDto {
    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    private String name;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
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
