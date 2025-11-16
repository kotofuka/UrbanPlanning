package com.tversu.urbanplanning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @NotBlank(message = "Имя города не может быть пустым")
    @Size(min = 1, max = 100, message = "Имя города должно быть от 1 до 100 символов")
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Street> streets = new HashSet<>();

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }

    public @NotBlank(message = "Название города не может быть пустым") String getName() {
        return name;
    }
}
