package com.tversu.urbanplanning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creators")
public class Creator {
    @Id
    @Column(name = "full_name", length = 200)
    private String fullName;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipationInCreation> participations;
}
