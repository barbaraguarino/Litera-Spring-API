package com.guarino.literaspringapi.application.publisher.dto;

import com.guarino.literaspringapi.shared.validation.TrimOnly;
import com.guarino.literaspringapi.shared.validation.UpperTrim;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequestDTO {

        @NotBlank(message = "O nome não pode ser vazio ou nulo.")
        @Size(max = 150, message = "O nome precisa ter no máximo 150 caracteres.")
        @UpperTrim
        private String name;

        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "A data deve estar no formato yyyy-MM-dd."
        )
        @Size(max = 10)
        @TrimOnly
        private String foundationDate;

        @Size(max = 700, message = "A descrição precisa ter no máximo 700 caracteres.")
        @TrimOnly
        private String description;

        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Formato de e-mail inválido."
        )
        @Size(max = 150)
        @UpperTrim
        private String email;

        @Pattern(
                regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9\\-]+(\\.[a-zA-Z]{2,}){1,}(/.*)?$",
                message = "O site deve ser uma URL válida."
        )
        @Size(max = 200)
        @UpperTrim
        private String website;

        @Size(max = 20)
        @UpperTrim
        private String taxId;

        @Pattern(
                regexp = "^[0-9]{8,15}$",
                message = "O número de telefone deve conter apenas dígitos (sem espaços, traços ou símbolos) e ter entre 8 e 15 dígitos."
        )
        @Size(max = 15)
        @TrimOnly
        private String telephone;

}

