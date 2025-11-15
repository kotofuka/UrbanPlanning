package com.tversu.urbanplanning.dto;

import com.tversu.urbanplanning.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {
    private String cityName;

    public CityDto(City city) {
        this.cityName = city.getName();
    }
}
