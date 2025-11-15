package com.tversu.urbanplanning.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @NotBlank(message = "Название города не может быть пустым")
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Street> streets;

    public @NotBlank(message = "Название города не может быть пустым") String getName() {
        return name;
    }
}
