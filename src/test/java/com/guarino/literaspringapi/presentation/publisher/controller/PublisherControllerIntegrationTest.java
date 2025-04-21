package com.guarino.literaspringapi.presentation.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PublisherController.class)
@Import(PublisherControllerIntegrationTest.MockConfig.class)
@ActiveProfiles("test")
class PublisherControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PublisherRequestDTO request;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PublisherService publisherService() {
            return Mockito.mock(PublisherService.class);
        }
    }

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

    @Nested
    class CreatePublisher{

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando o nome for vazio")
        void shouldThrowValidationExceptionWhenNameIsEmpty() throws Exception {
            request.setName("");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*nome.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando email for nulo")
        void shouldThrowValidationExceptionWhenNameIsNull() throws Exception {
            request.setName(null);

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*nome.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando o nome tiver menos de 3 caracteres.")
        void shouldThrowValidationExceptionWhenNameHasLessThanThreeCharacters() throws Exception {
            request.setName("S3");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*nome.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando data não tiver a formação desejada.")
        void shouldThrowValidationExceptionWhenDateFormatIsInvalid() throws Exception {
            request.setFoundationDate("25-10-2025");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*foundationDate.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando telefone tiver mais de 15 caracteres.")
        void shouldThrowValidationExceptionWhenPhoneNumberExceedsMaxLength() throws Exception {
            request.setTelephone("1234567891234567891");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*telephone.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando o e-mail for inválido")
        void shouldThrowValidationExceptionWhenEmailIsInvalid() throws Exception {
            request.setEmail("emaildominio.com");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*email.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando telefone tiver caracteres diferentes de números.")
        void shouldThrowValidationExceptionWhenPhoneNumberHasNonNumericCharacters() throws Exception {
            request.setTelephone("125-548");

            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages").isArray())
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*telephone.*/i)]").exists());
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

