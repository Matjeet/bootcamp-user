package com.example.bootcamp.user.configuration.exceptionhandle;

import com.example.bootcamp.user.domain.exception.StudentExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.bootcamp.user.domain.util.StudentMessage.ERROR;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(StudentExistsException.class)
    public ResponseEntity<String> handleStudentExistException(StudentExistsException studentExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR + studentExistsException.getMessage());
    }
}
