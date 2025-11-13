package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.ParticipationInCreationId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Достопримечательность не может быть пустой")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "landmark_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name"),
            @JoinColumn(name = "street_name", referencedColumnName = "street_name")
    })
    private Landmark landmark;

    @Id
    @NotNull(message = "Создатель не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "creator_full_name")
    private Creator creator;
}
