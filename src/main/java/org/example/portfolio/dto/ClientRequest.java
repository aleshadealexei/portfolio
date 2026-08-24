package org.example.portfolio.dto;

import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        String lastName,
        String firstName,
        String patronymic,
        String inn,
        String snils,
        String phone,
        @NotNull(message = "ID региона обязателен")
        Long regionId
) {
}
