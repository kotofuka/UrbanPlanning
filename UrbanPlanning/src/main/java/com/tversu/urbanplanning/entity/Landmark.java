package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.LandmarkId;
import jakarta.persistence.*;
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
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Id
    @ManyToOne
    @JoinColumn(name = "city_name")
    private City city;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_name", referencedColumnName = "city_name", insertable = false, updatable = false)
    })
    private Street street;

    @OneToMany(mappedBy = "landmark", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipationInCreation> participations;
}
