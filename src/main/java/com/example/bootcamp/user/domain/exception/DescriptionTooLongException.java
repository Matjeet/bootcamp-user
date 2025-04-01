package com.example.bootcamp.user.domain.exception;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String message) {
        super(message);
    }
}
