package com.guarino.literaspringapi.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {

    private String errorType;
    private String timestamp;
    private String className;
    private String message;

    public ErrorResponseDTO(String errorType, String className, String message) {
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now().toString();
        this.className = className;
        this.message = message;
    }

}
