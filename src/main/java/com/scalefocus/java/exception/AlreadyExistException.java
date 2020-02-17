package com.scalefocus.java.exception;

public class AlreadyExistException extends Exception {

    private String message;

    public AlreadyExistException() {
        message = "Entity already exist";
    }

    public AlreadyExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
