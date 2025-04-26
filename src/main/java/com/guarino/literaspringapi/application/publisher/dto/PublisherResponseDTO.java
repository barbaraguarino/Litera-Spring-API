package com.guarino.literaspringapi.application.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublisherResponseDTO(
        @NotNull String idPublisher,
        @NotBlank @Size(max = 150) String name,
        @Size(max = 10) String foundationDate,
        @Size(max = 700) String description,
        @Size(max = 150) String email,
        @Size(max = 200) String website,
        @Size(max = 20) String taxId,
        @Size(max = 15) String telephone
){}
