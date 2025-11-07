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
public class LandmarkId implements Serializable {
    private String name;
    private City city;
    private Street street;

    @Override
    public int hashCode() {
        return (name + city.getName() + street.getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LandmarkId other = (LandmarkId) obj;
        return name.equals(other.name) &&
                city.equals(other.city) &&
                street.equals(other.street);
    }
}
