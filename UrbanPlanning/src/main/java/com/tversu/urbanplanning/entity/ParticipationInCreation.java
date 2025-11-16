package com.tversu.urbanplanning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tversu.urbanplanning.entity.IdClass.ParticipationInCreationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "participations_in_creation")
public class ParticipationInCreation {

    @EmbeddedId
    private ParticipationInCreationId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "landmark_name", referencedColumnName = "name", insertable = false, updatable = false),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name", insertable = false, updatable = false),
            @JoinColumn(name = "street_name", referencedColumnName = "street_name", insertable = false, updatable = false)
    })
    @JsonIgnore
    private Landmark landmark;

    @ManyToOne
    @JoinColumn(name = "creator_full_name", insertable = false, updatable = false)
    @JsonIgnore
    private Creator creator;

    public ParticipationInCreationId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ParticipationInCreation{" +
                "landmarkName=\'" + id.toString() +
                '}';
    }
}
