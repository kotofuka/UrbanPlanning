package com.tversu.urbanplanning.entity.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StreetId implements Serializable {
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "city_name", length = 100)
    private String cityName;

    public StreetId() {}

    public StreetId(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StreetId that = (StreetId) obj;
        return Objects.equals(name, that.name) &&
                Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityName);
    }

    @Override
    public String toString() {
        return name + " (city: " + cityName + ")";
    }
}
