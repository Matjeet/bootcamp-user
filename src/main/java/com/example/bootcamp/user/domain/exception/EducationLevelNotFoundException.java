package com.example.bootcamp.user.domain.exception;

public class EducationLevelNotFoundException extends RuntimeException {
    public EducationLevelNotFoundException(String message) {
        super(message);
    }
}
