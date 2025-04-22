package com.guarino.literaspringapi.presentation.publisher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.service.PublisherService;

import com.guarino.literaspringapi.shared.validation.CaseId;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PublisherController.class)
@Import(PublisherControllerIntegrationTest.MockConfig.class)
@ActiveProfiles("test")
@Transactional
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
        @CaseId("CT-003")
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
        @DisplayName("Deve lançar uma exceção de validação quando nome for nulo")
        @CaseId("CT-010")
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
        @CaseId("CT-011")
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
        @CaseId("CT-007")
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
        @CaseId("CT-005")
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
        @CaseId("CT-004")
        void shouldThrowValidationExceptionWhenEmailIsInvalid() throws Exception {
            request.setEmail("email.dom.com");
            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorType").value("Erro de validação."))
                    .andExpect(jsonPath("$.messages[?(@ =~ /.*email.*/i)]").exists());
        }

        @Test
        @DisplayName("Deve lançar uma exceção de validação quando telefone tiver caracteres diferentes de números.")
        @CaseId("CT-012")
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

    }

    @Nested
    class SqlInjectionTest{

        @Test
        @DisplayName("Deve aceitar input suspeito sem executar SQL malicioso")
        @CaseId("CT-013")
        void shouldTreatSqlInjectionLikeNormalTextAndCreateEntity() throws Exception {
            request.setName("Nome da Editora'; DROP TABLE publisher; --");
            mockMvc.perform(post("/api/publisher")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
    }
}

