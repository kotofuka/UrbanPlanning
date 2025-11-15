package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.LandmarkId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "landmarks")
public class Landmark {

    @EmbeddedId
    private LandmarkId id;

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

    @ManyToOne
    @JoinColumn(name = "city_name", insertable = false, updatable = false)
    private City city;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name", insertable = false, updatable = false),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name", insertable = false, updatable = false)
    })
    private Street street;

    @OneToMany(mappedBy = "landmark", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ParticipationInCreation> participations = new HashSet<>();
}
