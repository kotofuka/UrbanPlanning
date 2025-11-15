package com.tversu.urbanplanning.entity;

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
@Table(name = "creators")
public class Creator {

    @Id
    @NotBlank(message = "ФИО не может быть пустым")
    @Column(name = "full_name", length = 200)
    private String fullName;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ParticipationInCreation> participations = new HashSet<>();
}
