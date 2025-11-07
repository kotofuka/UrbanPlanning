package com.tversu.urbanplanning.entity.IdClass;

import com.tversu.urbanplanning.entity.Creator;
import com.tversu.urbanplanning.entity.Landmark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationInCreationId implements Serializable {
    private Landmark landmark;
    private Creator creator;

    @Override
    public int hashCode() {
        return (landmark.getName() + creator.getFullName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ParticipationInCreationId other = (ParticipationInCreationId) obj;
        return landmark.equals(other.landmark) && creator.equals(other.creator);
    }
}
