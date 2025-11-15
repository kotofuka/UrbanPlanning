package com.tversu.urbanplanning.entity;

import com.tversu.urbanplanning.entity.IdClass.StreetId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "streets")
public class Street {

    @EmbeddedId
    private StreetId id;

    @ManyToOne
    @JoinColumn(name = "city_name", insertable = false, updatable = false)
    private City city;

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Building> buildings = new HashSet<>();

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Landmark> landmarks = new HashSet<>();
}
