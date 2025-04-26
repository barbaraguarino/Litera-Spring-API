package com.guarino.literaspringapi.application.author.service;

import com.guarino.literaspringapi.application.author.dto.AuthorRequestDTO;
import com.guarino.literaspringapi.application.author.dto.AuthorResponseDTO;
import com.guarino.literaspringapi.application.author.mapper.AuthorMapper;
import com.guarino.literaspringapi.domain.author.repository.AuthorRepository;
import com.guarino.literaspringapi.shared.util.UniquenessValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final UniquenessValidator uniquenessValidator;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, UniquenessValidator uniquenessValidator) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.uniquenessValidator = uniquenessValidator;
    }

    public AuthorResponseDTO createAuthor(@Valid AuthorRequestDTO request) {
        var author = authorMapper.toEntity(request);
        uniquenessValidator.validateMultiple("Autor(a)", Map.of(
                "email", new UniquenessValidator.ValidationEntry(author.getEmail(),
                        v -> authorRepository.existsByEmail((String) v))
        ));
        author = authorRepository.save(author);
        return authorMapper.toResponseDTO(author);
    }
}
