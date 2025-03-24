package com.example.bootcamp.user.ports.driving.controller;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;
import com.example.bootcamp.user.ports.driving.mapper.IStudentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentMessage.CREATED_MESSAGE;
import static com.example.bootcamp.user.domain.util.StudentMessage.MESSAGE_KEY;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IStudentMapper studentMapper;
    private final IStudentServicePort studentServicePort;

    private final Map<String, String> simpleResponse = new HashMap<>();

    @PostMapping("/student")
    public ResponseEntity<Map<String, String>> saveStudent(@Valid @RequestBody StudentRegisterDto studentRegisterDto){
        StudentInstitution studentInstitution = studentMapper.toStudentInstitution(studentRegisterDto);
        Student student = studentMapper.toStudent(studentRegisterDto);
        studentServicePort.save(student, studentInstitution);

        simpleResponse.put(MESSAGE_KEY, CREATED_MESSAGE);

        return ResponseEntity.status(HttpStatus.CREATED).body(simpleResponse);
    }
}
