package com.example.bootcamp.user.domain.exception;

public class InvalidCellphoneException extends RuntimeException {
    public InvalidCellphoneException(String message) {
        super(message);
    }
}
