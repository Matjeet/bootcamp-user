package com.example.bootcamp.user.domain.exception;

public class ProfileNotUpdateException extends RuntimeException {
    public ProfileNotUpdateException(String message) {
        super(message);
    }
}
