package com.tversu.urbanplanning.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ValidatorUtil {

    public void requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не может быть пустым");
        }
    }

    public void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " не может быть null");
        }
    }

    public void validateRange(BigDecimal value, BigDecimal min, BigDecimal max, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " не может быть null");
        }
        if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            throw new IllegalArgumentException(fieldName + " должна быть в диапазоне [" + min + ", " + max + "]");
        }
    }

    public <T> void requirePresent(Optional<T> optional, String fieldName) {
        if (optional.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " не найден");
        }
    }

    public <T> void requireAbsent(Optional<T> optional, String fieldName) {
        if (optional.isPresent()) {
            throw new IllegalArgumentException(fieldName + " уже существует");
        }
    }
}
