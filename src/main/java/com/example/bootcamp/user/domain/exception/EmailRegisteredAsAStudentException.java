package com.example.bootcamp.user.domain.exception;

public class EmailRegisteredAsAStudentException extends RuntimeException {
    public EmailRegisteredAsAStudentException(String message) {
        super(message);
    }
}
