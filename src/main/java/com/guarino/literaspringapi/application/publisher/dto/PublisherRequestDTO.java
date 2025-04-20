package com.guarino.literaspringapi.application.publisher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherRequestDTO(
        @NotBlank @Size(max = 150) String name,
        @Size(max = 10) String foundationData,
        @Size(max = 700) String description,
        @Size(max = 150) String email,
        @Size(max = 100) String countryHeadquarter
){}
