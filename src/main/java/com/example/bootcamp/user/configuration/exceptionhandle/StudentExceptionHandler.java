package com.example.bootcamp.user.configuration.exceptionhandle;

import com.example.bootcamp.user.domain.exception.StudentExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentMessage.ERROR;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(StudentExistsException.class)
    public ResponseEntity<String> handleStudentExistException(StudentExistsException studentExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + studentExistsException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
