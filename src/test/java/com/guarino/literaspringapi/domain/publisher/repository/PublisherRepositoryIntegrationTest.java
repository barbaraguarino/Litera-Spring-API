package com.guarino.literaspringapi.domain.publisher.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.domain.publisher.entity.Publisher;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
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

    @Test
    @DisplayName("Deve persistir o texto perigoso no banco sem executar comandos maliciosos")
    void shouldSaveMaliciousTextAsPlainString() throws Exception {
        String maliciousName = "Nome da Editora'; DROP TABLE publisher; --";
        PublisherRequestDTO request = new PublisherRequestDTO(
                maliciousName,
                "2025-04-20",
                "Descrição válida.",
                "email@dominio.com",
                "https://www.google.com",
                "123456789",
                "12345678901234"
        );

        mockMvc.perform(post("/api/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        List<Publisher> allPublishers = publisherRepository.findAll();
        assertFalse(allPublishers.isEmpty());

        boolean found = allPublishers.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(maliciousName));

        assertTrue(found, "O nome com SQL Injection deveria ter sido salvo como texto normal.");
    }
}

