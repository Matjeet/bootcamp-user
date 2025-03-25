package com.example.bootcamp.user.domain.exception;

public class StaffRolNotFoundException extends RuntimeException {
    public StaffRolNotFoundException(String message) {
        super(message);
    }
}
