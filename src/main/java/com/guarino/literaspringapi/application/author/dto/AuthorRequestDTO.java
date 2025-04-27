package com.guarino.literaspringapi.application.author.dto;

import com.guarino.literaspringapi.shared.validation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class AuthorRequestDTO {

    @NotBlank(message = "O nome deve ser informado")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
    @OnlyLetters
    @UpperTrim
    private String name;

    @NotBlank(message = "O sobrenome deve ser informado")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @OnlyLetters
    @UpperTrim
    private String surname;

    @NotBlank(message = "O pseudônimo deve ser informado.")
    @Size(max = 150, message = "O pseudônimo deve ter no máximo 150 caracteres.")
    @UpperTrim
    private String pseudonym;

    @Size(max = 150, message = "O email deve conter no máximo 150 caracteres.")
    @Email(message = "Formato de e-mail inválido.")
    @UpperTrim
    private String email;

    @Size(max = 10, message = "A data de nascimento deve conter no máximo 10 caracteres.")
    @DataFormat
    private String birthDate;

    @Size(max = 10, message = "A data de falecimento deve conter no máximo 10 caracteres.")
    @DataFormat
    private String deathDate;

    @Size(max = 200, message = "O site deve conter no máximo 200 caracteres.")
    @URL(message = "O site deve ser uma URL válida.")
    @UpperTrim
    private String website;

    @Size(max = 800, message = "A descrição deve conter no máximo 800 caracteres.")
    @TrimOnly
    private String description;

    private MultipartFile image;

    @Size(max = 100, message = "A nacionalidade deve conter no máximo 100 caracteres.")
    @OnlyLetters
    @UpperTrim
    private String nationality;
}
