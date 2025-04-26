package com.guarino.literaspringapi.application.publisher.mapper;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.domain.publisher.entity.Publisher;

import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        return new PublisherResponseDTO(
                publisher.getIdPublisher().toString(),
                publisher.getName(),
                publisher.getFoundationDate(),
                publisher.getDescription(),
                publisher.getEmail(),
                publisher.getWebsite(),
                publisher.getTaxId(),
                publisher.getTelephone()
        );
    }

    public Publisher toEntity(PublisherRequestDTO publisherRequestDTO) {
        return new Publisher(
                publisherRequestDTO.getName(),
                publisherRequestDTO.getFoundationDate(),
                publisherRequestDTO.getDescription(),
                publisherRequestDTO.getEmail(),
                publisherRequestDTO.getWebsite(),
                publisherRequestDTO.getTelephone(),
                publisherRequestDTO.getTaxId()
        );
    }
}

