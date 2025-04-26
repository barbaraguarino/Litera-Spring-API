package com.guarino.literaspringapi.shared.handler;

import com.guarino.literaspringapi.shared.dto.ErrorResponseDTO;
import com.guarino.literaspringapi.shared.exception.ResourceAlreadyExistsException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Erro de validação.",
                "MethodArgumentNotValidException",
                getValidationMessages(fieldErrors),
                "VALIDATION_ERROR"
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Error de cadastro duplicado.",
                "ResourceAlreadyExistsException",
                List.of(ex.getMessage()),
                "RESOURCE_EXISTS_ERROR"
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Erro interno no servidor.",
                ex.getClass().getSimpleName(),
                List.of("Ocorreu um erro inesperado. Por favor, entre em contato com o suporte."),
                "GENERIC_SERVER_ERROR"
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Erro de integridade de dados.",
                ex.getClass().getSimpleName(),
                List.of("A entrada fornecida contém dados inválidos ou potencialmente perigosos."),
                "DATA_INTEGRITY_VIOLATION_ERROR"
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
    
    private List<String> getValidationMessages(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}


