package com.guarino.literaspringapi.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {

    private String errorType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;
    private String className;
    private String message;
    private String errorCode;

    public ErrorResponseDTO(String errorType, String className, String message, String errorCode) {
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now().toString();
        this.className = className;
        this.message = message;
        this.errorCode = errorCode;
    }
}
