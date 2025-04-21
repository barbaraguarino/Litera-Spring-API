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

        Publisher publisher = publisherMapper.toEntity(request);

        assertEquals("EDITORA ABRIL", publisher.getName());
        assertEquals("A Editora Abril é uma das maiores editoras de revistas do Brasil, " +
                "com publicações em diversos segmentos, incluindo revistas de notícias, " +
                "entretenimento e estilo de vida.", publisher.getDescription());
        assertEquals("CONTATO@ABRIL.COM.BR", publisher.getEmail());
        assertEquals("HTTPS://WWW.ABRIL.COM.BR", publisher.getWebsite());
        assertEquals("02183757000193", publisher.getTaxId());
        assertEquals("1134567890", publisher.getTelephone());

    }
}

