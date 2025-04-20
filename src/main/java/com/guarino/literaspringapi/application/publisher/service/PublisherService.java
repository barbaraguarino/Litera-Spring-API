package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.application.publisher.mapper.PublisherMapper;
import com.guarino.literaspringapi.domain.publisher.repository.PublisherRepository;

import com.guarino.literaspringapi.shared.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        publisher = publisherRepository.save(publisher);

        return publisherMapper.toResponseDTO(publisher);
    }

}
