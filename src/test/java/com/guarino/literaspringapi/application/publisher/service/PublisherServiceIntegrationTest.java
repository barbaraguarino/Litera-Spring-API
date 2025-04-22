package com.guarino.literaspringapi.application.publisher.service;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.dto.PublisherResponseDTO;
import com.guarino.literaspringapi.shared.validation.CaseId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PublisherServiceIntegrationTest {

    @Autowired
    private PublisherService publisherService;

    private PublisherRequestDTO request;

    @Nested
    class CreatePublisher{

        @BeforeEach
        void setUp() {
            request = new PublisherRequestDTO(
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
        }

        @Test
        @DisplayName("Deve criar uma editora com campos transformados.")
        @CaseId("CT-008")
        void shouldCreatePublisherWithTransformedFields() {

            PublisherResponseDTO response = publisherService.createPublisher(request);

            assertEquals("EDITORA ABRIL", response.name());
            assertEquals("A Editora Abril é uma das maiores editoras de revistas do Brasil, " +
                    "com publicações em diversos segmentos, incluindo revistas de notícias, " +
                    "entretenimento e estilo de vida.", response.description());
            assertEquals("CONTATO@ABRIL.COM.BR", response.email());
            assertEquals("HTTPS://WWW.ABRIL.COM.BR", response.website());
            assertEquals("02183757000193", response.taxId());
            assertEquals("1134567890", response.telephone());
            assertNotNull(response.idPublisher());
        }
    }
}
