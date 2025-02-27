package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.entity.StudentEntity;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentInstitutionRepository;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StudentAdapter implements IStudentPersistencePort {

    private final IStudentRepository studentRepository;
    private final IStudentEntityMapper studentEntityMapper;
    private final IStudentInstitutionRepository studentInstitutionRepository;
    private final IStudentInstitutionEntityMapper studentInstitutionEntityMapper;

    @Override
    public void save(Student student, StudentInstitution studentInstitution) {
        studentRepository.save(studentEntityMapper.toEntity(student));
        studentInstitutionRepository.save(studentInstitutionEntityMapper.toEntity(studentInstitution));
    }

    @Override
    public Student findByEmailOrIdentification(String email, String identification){
       Optional<StudentEntity> studentFromDB = studentRepository.findByEmailOrIdentification(email, identification);
       return studentEntityMapper.toModel(studentFromDB.orElse(null));
    }
}
