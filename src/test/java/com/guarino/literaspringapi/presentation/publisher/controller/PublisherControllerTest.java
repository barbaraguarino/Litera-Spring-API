package com.guarino.literaspringapi.presentation.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.service.PublisherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PublisherController.class)
@Import(PublisherControllerTest.MockConfig.class)
class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PublisherService publisherService() {
            return Mockito.mock(PublisherService.class);
        }
    }

    @Nested
    class CreatePublisher{

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando o nome for nulo ou vazio")
        void shouldThrowValidationExceptionWhenNameIsNullOrEmpty() throws Exception {
            PublisherRequestDTO request = new PublisherRequestDTO(
                    "",
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
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*nome.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando o e-mail for inválido")
        void shouldThrowValidationExceptionWhenEmailIsInvalid() throws Exception {
            PublisherRequestDTO requestWithInvalidEmail = new PublisherRequestDTO(
                    "Nome da Editora",
                    "2025-04-20",
                    "Descrição válida.",
                    "email-inválido",  // Email inválido
                    "https://www.google.com",
                    "123456789",
                    "12345678901234"
            );

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestWithInvalidEmail)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*email.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve aceitar input suspeito sem executar SQL malicioso")
        void shouldTreatSqlInjectionLikeNormalTextAndCreateEntity() throws Exception {
            PublisherRequestDTO requestWithMaliciousSQL = new PublisherRequestDTO(
                    "Nome da Editora'; DROP TABLE publisher; --",
                    "2025-04-20",
                    "Descrição válida.",
                    "email@dominio.com",
                    "www.google.com",
                    "123456789",
                    "12345678901234"
            );

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestWithMaliciousSQL)))
                    .andExpect(status().isCreated());
        }
    }
}

