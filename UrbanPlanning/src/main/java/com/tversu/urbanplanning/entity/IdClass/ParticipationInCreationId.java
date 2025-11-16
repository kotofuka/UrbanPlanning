package com.tversu.urbanplanning.entity.IdClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipationInCreationId implements Serializable {
    @Column(name = "landmark_name", length = 200)
    private String landmarkName;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "street_name", length = 100)
    private String streetName;

    @Column(name = "creator_full_name", length = 200)
    private String creatorFullName;

    public ParticipationInCreationId() {}

    public ParticipationInCreationId(String landmarkName, String cityName, String streetName, String creatorFullName) {
        this.landmarkName = landmarkName;
        this.cityName = cityName;
        this.streetName = streetName;
        this.creatorFullName = creatorFullName;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
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

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ParticipationInCreationId that = (ParticipationInCreationId) obj;
        return Objects.equals(landmarkName, that.landmarkName) &&
                Objects.equals(cityName, that.cityName) &&
                Objects.equals(streetName, that.streetName) &&
                Objects.equals(creatorFullName, that.creatorFullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landmarkName, cityName, streetName, creatorFullName);
    }

    @Override
    public String toString() {
        return landmarkName + "\', creator=\'" + creatorFullName + "\', city=\'" + cityName + "\', street=\'" + streetName + "\'";
    }
}
