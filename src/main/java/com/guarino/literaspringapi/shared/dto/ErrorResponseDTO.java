package com.guarino.literaspringapi.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class ErrorResponseDTO {

    private String errorType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;
    private String className;
    private List<String> messages;
    private String errorCode;

    public ErrorResponseDTO(String errorType, String className, List<String> messages, String errorCode) {
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.className = className;
        this.messages = messages;
        this.errorCode = errorCode;
    }
}
