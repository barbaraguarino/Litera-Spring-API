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
                "Editora Abril   ",
                "1950-03-04",
                "   A Editora Abril é uma das maiores editoras de revistas do Brasil, " +
                        "com publicações em diversos segmentos, incluindo revistas de notícias, " +
                        "entretenimento e estilo de vida.   ",
                "  contato@abril.com.br",
                "https://www.abril.com.br  ",
                " 02183757000193",
                "1134567890 "
        );

        PublisherResponseDTO response = publisherService.createPublisher(request);

        assertEquals("EDITORA ABRIL", response.name());
        assertEquals("A Editora Abril é uma das maiores editoras de revistas do Brasil, " +
                "com publicações em diversos segmentos, incluindo revistas de notícias, " +
                "entretenimento e estilo de vida.", response.description());
        assertEquals("CONTATO@ABRIL.COM.BR", response.email());
        assertEquals("HTTPS://WWW.ABRIL.COM.BR", response.website());
        assertEquals("02183757000193", response.taxId());
        assertEquals("1134567890", response.telephone());
    }
}
