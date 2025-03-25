package com.example.bootcamp.user.domain.exception;

public class StaffMemberExistException extends RuntimeException {
    public StaffMemberExistException(String message) {
        super(message);
    }
}
