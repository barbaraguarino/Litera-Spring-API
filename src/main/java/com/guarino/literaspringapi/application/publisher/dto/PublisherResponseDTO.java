package com.guarino.literaspringapi.application.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublisherResponseDTO(
        @NotNull String idPublisher,
        @NotBlank String name,
        String foundation_data,
        String description,
        String email,
        String country_headquarter
){}
