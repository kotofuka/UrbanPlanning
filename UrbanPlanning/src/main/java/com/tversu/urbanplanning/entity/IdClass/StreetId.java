package com.tversu.urbanplanning.entity.IdClass;

import com.tversu.urbanplanning.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreetId implements Serializable {
    private String name;
    private City city;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        StreetId st = (StreetId) obj;
        return name.equals(st.name) && city.equals(st.city);
    }

    @Override
    public int hashCode() {
        return (name + city.getName()).hashCode();
    }
}
