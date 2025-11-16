package com.tversu.urbanplanning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Полное имя создателя не может быть пустым")
    @Size(min = 1, max = 200, message = "Полное имя должно быть от 1 до 200 символов")
    @Column(name = "full_name", length = 200)
    private String fullName;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ParticipationInCreation> participations = new HashSet<>();

    @Override
    public String toString() {
        return "Creator{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

    public @NotBlank(message = "ФИО не может быть пустым") String getFullName() {
        return fullName;
    }
}
