package com.guarino.literaspringapi.application.author.service;

import com.guarino.literaspringapi.application.author.dto.AuthorRequestDTO;
import com.guarino.literaspringapi.application.author.dto.AuthorResponseDTO;
import com.guarino.literaspringapi.domain.author.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDTO createAuthor(@Valid AuthorRequestDTO request) {

        return null;
    }
}
