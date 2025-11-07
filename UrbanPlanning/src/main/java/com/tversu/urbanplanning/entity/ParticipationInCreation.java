package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.ParticipationInCreationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@IdClass(ParticipationInCreationId.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participations_in_creation")
public class ParticipationInCreation {
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "landmark_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name"),
            @JoinColumn(name = "street_name", referencedColumnName = "street_name")
    })
    private Landmark landmark;

    @Id
    @ManyToOne
    @JoinColumn(name = "creator_full_name")
    private Creator creator;
}
