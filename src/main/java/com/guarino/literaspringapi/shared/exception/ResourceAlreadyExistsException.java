package com.guarino.literaspringapi.shared.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s com %s '%s' já existe no sistema.", resourceName, fieldName, fieldValue));
    }
}