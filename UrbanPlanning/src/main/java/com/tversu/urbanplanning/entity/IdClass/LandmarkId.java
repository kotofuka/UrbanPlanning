package com.tversu.urbanplanning.entity.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LandmarkId implements Serializable {
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "street_name", length = 100)
    private String streetName;

    public LandmarkId() {}

    public LandmarkId(String name, String cityName, String streetName) {
        this.name = name;
        this.cityName = cityName;
        this.streetName = streetName;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LandmarkId that = (LandmarkId) obj;
        return Objects.equals(name, that.name) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(streetName, that.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityName, streetName);
    }

    @Override
    public String toString() {
        return name + "\', city=\'" + cityName + "\', street=\'" + streetName + "\'";
    }
}
