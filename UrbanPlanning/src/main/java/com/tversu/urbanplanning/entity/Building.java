package com.tversu.urbanplanning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tversu.urbanplanning.entity.IdClass.BuildingId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    @EmbeddedId
    private BuildingId id;

    @ManyToOne
    @JoinColumn(name = "city_name", insertable = false, updatable = false)
    @JsonIgnore
    private City city;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name", insertable = false, updatable = false),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name", insertable = false, updatable = false)
    })
    @JsonIgnore
    private Street street;

    @Override
    public String toString() {
        return "Building{" +
                "name=" + id.toString() +
                '}';
    }

    public BuildingId getId() {
        return id;
    }
}
