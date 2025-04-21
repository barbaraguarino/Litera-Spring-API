package com.guarino.literaspringapi.config.aop;

import com.guarino.literaspringapi.application.publisher.dto.PublisherRequestDTO;
import com.guarino.literaspringapi.application.publisher.mapper.PublisherMapper;
import com.guarino.literaspringapi.domain.publisher.entity.Publisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class FieldTransformationAspectIntegrationTest {

    @Autowired
    private PublisherMapper publisherMapper;

    @Test
    @DisplayName("Deve transformar campos antes de entidade.")
    void shouldTransformFieldsBeforeToEntity() {
        PublisherRequestDTO request = new PublisherRequestDTO(
                " Nome da Editora ",
                "2025-04-20",
                "   Descrição válida.   ",
                "email@dominio.com",
                "https://www.google.com",
                "123456789",
                "12345678901234"
        );

        Publisher publisher = publisherMapper.toEntity(request);

        // Verificando a transformação dos campos
        assertEquals("NOME DA EDITORA", publisher.getName()); // O nome deve ser uppercased
        assertEquals("Descrição válida.", publisher.getDescription()); // A descrição deve ser trimada
    }
}

