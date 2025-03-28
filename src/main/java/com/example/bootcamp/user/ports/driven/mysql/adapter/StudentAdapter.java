package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StudentAdapter implements IStudentPersistencePort {

    private final IStudentRepository studentRepository;
    private final IStudentEntityMapper studentEntityMapper;

    @Override
    public Optional<Student> findByEmailOrIdentification(String email, String identification){
       return studentRepository.findByEmailOrIdentification(email, identification).map(studentEntityMapper::toModel);
    }
}
