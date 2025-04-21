package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.application.publisher.mapper.PublisherMapper;
import com.guarino.literaspringapi.domain.publisher.repository.PublisherRepository;
import com.guarino.literaspringapi.shared.util.UniquenessValidator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    private final UniquenessValidator uniquenessValidator;

    public PublisherService(PublisherRepository publisherRepository,
                            PublisherMapper publisherMapper,
                            UniquenessValidator uniquenessValidator) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
        this.uniquenessValidator = uniquenessValidator;
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO request) {
        var publisher = publisherMapper.toEntity(request);
        uniquenessValidator.validateMultiple("Editora", Map.of(
                "email", new UniquenessValidator.ValidationEntry(publisher.getEmail(), v -> publisherRepository.existsByEmail((String) v)),
                "identificador fiscal", new UniquenessValidator.ValidationEntry(publisher.getTaxId(), v -> publisherRepository.existsByTaxId((String) v)),
                "site", new UniquenessValidator.ValidationEntry(publisher.getWebsite(), v -> publisherRepository.existsByWebsite((String) v)),
                "telefone", new UniquenessValidator.ValidationEntry(publisher.getTelephone(), v -> publisherRepository.existsByTelephone((String) v))
        ));
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toResponseDTO(publisher);
    }

}
