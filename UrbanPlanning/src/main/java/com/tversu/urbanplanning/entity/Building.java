package com.tversu.urbanplanning.entity;


import com.tversu.urbanplanning.entity.IdClass.BuildingId;
import jakarta.persistence.*;
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
    @Column(name = "house_number", length = 20)
    private String houseNumber;

    @Id
    @JoinColumn(name = "city_name")
    private City city;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "cty_name", insertable = false, updatable = false)
    })
    private Street street;
}
