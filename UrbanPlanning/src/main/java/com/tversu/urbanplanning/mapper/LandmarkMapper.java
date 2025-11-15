package com.tversu.urbanplanning.mapper;

import com.tversu.urbanplanning.dto.LandmarkDto.LandmarkResponse;
import com.tversu.urbanplanning.entity.Landmark;
import org.springframework.stereotype.Component;

@Component
public class LandmarkMapper {
    public static LandmarkResponse toResponse(Landmark landmark) {
        return new LandmarkResponse(
                landmark.getName(),
                landmark.getDescription(),
                landmark.getLatitude(),
                landmark.getLongitude(),
                landmark.getCity().getName(),
                landmark.getStreet().getName());
    }


}
