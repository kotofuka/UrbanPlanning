package com.tversu.urbanplanning.entity.IdClass;

import com.tversu.urbanplanning.entity.City;
import com.tversu.urbanplanning.entity.Street;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingId implements Serializable {
    private String houseNumber;
    private City city;
    private Street street;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BuildingId buildingId = (BuildingId) obj;
        return houseNumber.equals(buildingId.houseNumber) &&
                city.equals(buildingId.city) &&
                street.equals(buildingId.street);
    }

    @Override
    public int hashCode() {
        return (houseNumber + city.getName() + street.getName()).hashCode();
    }
}
