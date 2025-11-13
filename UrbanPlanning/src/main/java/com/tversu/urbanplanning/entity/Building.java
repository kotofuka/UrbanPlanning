package com.tversu.urbanplanning.entity;


import com.tversu.urbanplanning.entity.IdClass.BuildingId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@IdClass(BuildingId.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @NotBlank(message = "Номер дома не может быть пустым")
    @Column(name = "house_number", length = 20)
    private String houseNumber;

    @Id
    @NotNull(message = "Город не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "city_name")
    private City city;

    @Id
    @NotNull(message = "Улица не может быть пустой")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "cty_name", insertable = false, updatable = false)
    })
    private Street street;
}
