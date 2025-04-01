package com.example.bootcamp.user.domain.exception;

public class BadgesNotFoundException extends RuntimeException {
    public BadgesNotFoundException(String message) {
        super(message);
    }
}
