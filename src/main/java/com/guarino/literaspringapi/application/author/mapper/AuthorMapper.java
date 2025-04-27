package com.guarino.literaspringapi.application.author.mapper;

import com.guarino.literaspringapi.application.author.dto.AuthorRequestDTO;
import com.guarino.literaspringapi.application.author.dto.AuthorResponseDTO;
import com.guarino.literaspringapi.domain.author.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorResponseDTO toResponseDTO(Author author) {
        return new AuthorResponseDTO(
                author.getIdAuthor(),
                author.getName(),
                author.getSurname(),
                author.getPseudonym(),
                author.getEmail(),
                author.getBirthDate(),
                author.getDeathDate(),
                author.getWebsite(),
                author.getDescription(),
                author.getImageUrl(),
                author.getNationality()
        );
    }

    public Author toEntity(AuthorRequestDTO request) {
        return new Author(
                request.getName(),
                request.getSurname(),
                request.getPseudonym(),
                request.getEmail(),
                request.getBirthDate(),
                request.getDeathDate(),
                request.getWebsite(),
                request.getDescription()
        );
    }
}
