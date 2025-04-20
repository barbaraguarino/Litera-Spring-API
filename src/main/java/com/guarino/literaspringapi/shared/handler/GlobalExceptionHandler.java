package com.guarino.literaspringapi.shared.handler;

import com.guarino.literaspringapi.shared.dto.ErrorResponseDTO;
import com.guarino.literaspringapi.shared.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "ResourceAlreadyExistsException",
                ex.getClass().getName(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}

