package com.guarino.literaspringapi.application.author.service;

import com.guarino.literaspringapi.application.author.dto.AuthorRequestDTO;
import com.guarino.literaspringapi.application.author.dto.AuthorResponseDTO;
import com.guarino.literaspringapi.application.author.mapper.AuthorMapper;
import com.guarino.literaspringapi.domain.author.repository.AuthorRepository;
import com.guarino.literaspringapi.infrastructure.storage.StorageService;
import com.guarino.literaspringapi.shared.util.UniquenessValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final UniquenessValidator uniquenessValidator;
    private final StorageService storageService;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, UniquenessValidator uniquenessValidator, StorageService storageService) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.uniquenessValidator = uniquenessValidator;
        this.storageService = storageService;
    }

    public AuthorResponseDTO createAuthor(@Valid AuthorRequestDTO request){
        var author = authorMapper.toEntity(request);
        if(request.getImage() != null)
            author.setImageUrl(this.uploadImage(request.getImage()));
        uniquenessValidator.validateMultiple("Autor(a)", Map.of(
                "email", new UniquenessValidator.ValidationEntry(author.getEmail(),
                        v -> authorRepository.existsByEmail((String) v))
        ));
        author = authorRepository.save(author);
        return authorMapper.toResponseDTO(author);
    }

    private String uploadImage(MultipartFile image){
        try {
            return storageService.uploadFile(image, "Author's image.");
        }catch (IOException e){
            return  null;
        }
    }
}
