package com.example.bootcamp.user.domain.exception;

public class InvalidIdentificationException extends RuntimeException {
    public InvalidIdentificationException(String message) {
        super(message);
    }
}
