package com.tversu.urbanplanning.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@IdClass(Street.class)
@Table(name = "streets")
public class Street {
    @Id
    @Column(name = "name", length = 100)
    private String name;

    @Id
    @ManyToOne
    @JoinColumn(name = "city_name")
    private City city;

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Building> buildings;

    @OneToMany(mappedBy = "street", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Landmark> landmarks;
}
