package com.tversu.urbanplanning.entity.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BuildingId implements Serializable {
    @Column(name = "house_number", length = 20)
    private String houseNumber;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "street_name", length = 100)
    private String streetName;

    public BuildingId() {}

    public BuildingId(String houseNumber, String cityName, String streetName) {
        this.houseNumber = houseNumber;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
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
        BuildingId that = (BuildingId) obj;
        return Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(streetName, that.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseNumber, cityName, streetName);
    }

    @Override
    public String toString() {
        return houseNumber + "\' city=\'" + cityName + "\', street=\'" + streetName + "\'";
    }
}
