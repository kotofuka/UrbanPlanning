package com.tversu.urbanplanning.dto.BuildingDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {
    @NotBlank(message = "Номер дома не может быть пустым")
    @Size(min = 1, max = 20, message = "Номер дома должен быть от 1 до 20 символов")
    String houseNumber;

    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    String cityName;

    @NotBlank(message = "Имя улицы не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя улицы должно быть от 1 до 100 символов")
    String streetName;
}
