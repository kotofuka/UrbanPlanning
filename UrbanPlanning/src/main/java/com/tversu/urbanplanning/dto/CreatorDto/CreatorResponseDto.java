package com.tversu.urbanplanning.dto.CreatorDto;

import com.tversu.urbanplanning.entity.Creator;

public class CreatorResponseDto {
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
