package com.example.bootcamp.user.domain.exception;

public class DeveloperRolNotFoundException extends RuntimeException {
    public DeveloperRolNotFoundException(String message) {
        super(message);
    }
}
