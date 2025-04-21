package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.application.publisher.mapper.PublisherMapper;
import com.guarino.literaspringapi.domain.publisher.entity.Publisher;
import com.guarino.literaspringapi.domain.publisher.repository.PublisherRepository;
import com.guarino.literaspringapi.shared.util.UniquenessValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private PublisherMapper publisherMapper;
    @Mock
    private UniquenessValidator uniquenessValidator;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher publisher;
    private PublisherRequestDTO request;
    private PublisherResponseDTO response;
    private String uuid;

    @BeforeEach
    void setUp() {
        request = new PublisherRequestDTO(
                "Nome da Editora",
                "2025-04-20",
                "Uma descrição.",
                "email@dominio.com",
                "https://google.com",
                "123456789",
                "12345678901234"
        );

        publisher = new Publisher(
                "NOME DA EDITORA",
                "2025-04-20",
                "Uma descrição.",
                "EMAIL@DOMINIO.COM",
                "HTTPS://GOOGLE.COM",
                "123456789",
                "12345678901234"
        );

        uuid = UUID.randomUUID().toString();

        response = new PublisherResponseDTO(
                uuid,
                "NOME DA EDITORA",
                "2025-04-20",
                "Uma descrição.",
                "EMAIL@DOMINIO.COM",
                "HTTPS://GOOGLE.COM",
                "123456789",
                "12345678901234"
        );

    }

    @Nested
    class CreatePublisher{
        @Test
        @DisplayName("Deve criar a editora quando todos os campos forem válidos.")
        void shouldCreatePublisherWhenAllFieldsAreValid(){

            when(publisherMapper.toEntity(request)).thenReturn(publisher);
            when(publisherRepository.save(any(Publisher.class))).thenAnswer(invocation -> {
                Publisher publisherToSave = invocation.getArgument(0);
                publisherToSave.setIdPublisher(UUID.fromString(uuid));
                return publisherToSave;
            });
            when(publisherMapper.toResponseDTO(publisher)).thenReturn(response);

            PublisherResponseDTO result = publisherService.createPublisher(request);

            assertNotNull(result);
            assertEquals(response.name(), result.name());
            assertEquals(response.email(), result.email());
            verify(publisherRepository, times(1)).save(publisher);

        }



    }
}