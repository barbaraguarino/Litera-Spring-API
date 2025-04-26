package com.guarino.literaspringapi.application.author.dto;

import com.guarino.literaspringapi.shared.validation.DataFormat;
import com.guarino.literaspringapi.shared.validation.OnlyLetters;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

public record AuthorResponseDTO(
        @NotNull UUID idAuthor,
        @NotBlank @OnlyLetters String name,
        @NotBlank @OnlyLetters String surname,
        @NotBlank String pseudonym,
        @Email String email,
        @DataFormat String birthDate,
        @DataFormat String deathDate,
        @URL String website,
        String description,
        @URL String imagesUrl,
        @NotBlank @OnlyLetters String nationality
){}
