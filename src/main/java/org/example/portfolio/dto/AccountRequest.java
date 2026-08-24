package org.example.portfolio.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequest(
        String accountNumber,
        BigDecimal balance,
        @NotNull(message = "ID клиента обязателен")
        Long clientId
) {
}
