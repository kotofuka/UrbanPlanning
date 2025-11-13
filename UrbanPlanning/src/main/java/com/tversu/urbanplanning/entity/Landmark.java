package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.LandmarkId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@IdClass(LandmarkId.class)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "landmarks")
public class Landmark {
    @Id
    @NotBlank(message = "Название достопримечательности не может быть пустым")
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Широта не может быть пустой")
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @NotNull(message = "Долгота не может быть пустой")
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Id
    @NotNull(message = "Город не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "city_name")
    private City city;

    @Id
    @NotNull(message = "Улица не может быть пустой")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name", insertable = false, updatable = false)
    })
    private Street street;

    @OneToMany(mappedBy = "landmark", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipationInCreation> participations;
}
