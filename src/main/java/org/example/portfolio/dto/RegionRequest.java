package org.example.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

public record RegionRequest(
        @NotBlank(message = "Название региона обязательно")
        String name,
        String type,
        String federalDistrict
) {}
