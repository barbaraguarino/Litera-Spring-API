package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.application.publisher.mapper.PublisherMapper;
import com.guarino.literaspringapi.domain.publisher.repository.PublisherRepository;

import com.guarino.literaspringapi.shared.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.guarino.literaspringapi.shared.util.StringUtils.isNotBlank;

@Service
@Transactional
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public PublisherResponseDTO createPublisher(PublisherRequestDTO request) {

        var publisher = publisherMapper.toEntity(request);

        if(isNotBlank(publisher.getEmail()) && publisherRepository.existsByEmail(publisher.getEmail()))
            throw new ResourceAlreadyExistsException("Editora", "email", publisher.getEmail());

        if(isNotBlank(publisher.getTaxId()) && publisherRepository.existsByTaxId(publisher.getTaxId()))
            throw new ResourceAlreadyExistsException("Editora", "identificador fiscal", publisher.getTaxId());

        if(isNotBlank(publisher.getWebsite()) && publisherRepository.existsByWebsite(publisher.getWebsite()))
            throw new ResourceAlreadyExistsException("Editora", "site", publisher.getWebsite());

        if(isNotBlank(publisher.getTelephone()) && publisherRepository.existsByTelephone(publisher.getTelephone()))
            throw new  ResourceAlreadyExistsException("Editora", "telefone", publisher.getTelephone());

        publisher = publisherRepository.save(publisher);

        return publisherMapper.toResponseDTO(publisher);
    }

}
