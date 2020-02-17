package com.scalefocus.java.exception.models;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationExceptionResponse extends ExceptionResponse {

    private Map<String, String> fieldErrors;

    public ValidationExceptionResponse() {
        super("ValidationError");
    }

    public ValidationExceptionResponse(Map<String, String> fieldErrors) {
        super("ValidationError");
        this.fieldErrors = fieldErrors.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
