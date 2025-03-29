package com.example.bootcamp.user.domain.exception;

public class HobbiesNotFoundException extends RuntimeException {
    public HobbiesNotFoundException(String message) {
        super(message);
    }
}
