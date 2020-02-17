package com.scalefocus.java.exception;


import java.util.HashMap;

import java.util.List;

import java.util.Map;

import org.springframework.validation.FieldError;

public class ValidationException extends Exception {

    private Map<String, String> fieldErrors;

    public ValidationException(List<FieldError> fieldErrors) {
        this.fieldErrors = new HashMap<>();
        fieldErrors.forEach(fieldError -> this.fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
