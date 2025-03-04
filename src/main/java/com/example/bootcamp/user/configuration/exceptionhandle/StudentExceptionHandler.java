package com.example.bootcamp.user.configuration.exceptionhandle;

import com.example.bootcamp.user.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentMessage.MESSAGE_KEY;

@ControllerAdvice
public class StudentExceptionHandler {

    Map<String, String> responseException = new HashMap<>();

    @ExceptionHandler(StudentExistsException.class)
    public ResponseEntity<Map<String, String>> handleStudentExistException(StudentExistsException studentExistsException){
        responseException.put(MESSAGE_KEY, studentExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException invalidEmailException){
        responseException.put(MESSAGE_KEY, invalidEmailException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
    }

    @ExceptionHandler(InvalidCellphoneException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCellphoneException(InvalidCellphoneException invalidCellphoneException){
        responseException.put(MESSAGE_KEY, invalidCellphoneException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
    }

    @ExceptionHandler(InvalidIdentificationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidIdentificationException(InvalidIdentificationException invalidIdentificationException){
        responseException.put(MESSAGE_KEY, invalidIdentificationException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAgeException(InvalidAgeException invalidAgeException){
        responseException.put(MESSAGE_KEY, invalidAgeException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseException);
    }
}
