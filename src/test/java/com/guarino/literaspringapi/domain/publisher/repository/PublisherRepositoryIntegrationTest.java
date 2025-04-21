package com.guarino.literaspringapi.domain.publisher.repository;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.domain.publisher.entity.Publisher;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PublisherRepositoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PublisherRepository publisherRepository;

    private PublisherRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new PublisherRequestDTO(
                "Editora Abril",
                "1950-03-04",
                "A Editora Abril é uma das maiores editoras de revistas do Brasil, " +
                        "com publicações em diversos segmentos, incluindo revistas de notícias, " +
                        "entretenimento e estilo de vida.",
                "contato@abril.com.br",
                "https://www.abril.com.br",
                "02183757000193",
                "1134567890"
        );
    }

    @Test
    @DisplayName("Deve persistir o texto perigoso no banco sem executar comandos maliciosos")
    void shouldSaveMaliciousTextAsPlainString() throws Exception {
        String maliciousData = "Editora Abril; DROP TABLE publisher; --";
        request.setName(maliciousData);
        mockMvc.perform(post("/api/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
        List<Publisher> allPublishers = publisherRepository.findAll();
        assertFalse(allPublishers.isEmpty());
        boolean found = allPublishers.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(maliciousData));
        assertTrue(found, "O nome com SQL Injection deveria ter sido salvo como texto normal.");
    }
}

