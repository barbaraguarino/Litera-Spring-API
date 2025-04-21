package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PublisherServiceIntegrationTest {

    @Autowired
    private PublisherService publisherService;

    @Test
    void shouldCreatePublisherWithTransformedFields() {
        PublisherRequestDTO request = new PublisherRequestDTO(
                " Nome da Editora ",
                "2025-04-20",
                "   Descrição válida.   ",
                "email@dominio.com",
                "https://www.google.com",
                "123456789",
                "12345678901234"
        );

        PublisherResponseDTO response = publisherService.createPublisher(request);

        assertEquals("NOME DA EDITORA", response.name());
        assertEquals("Descrição válida.", response.description());
    }
}
