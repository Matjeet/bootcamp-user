package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.domain.spi.IStudentInstitutionPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentInstitutionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentInstitutionAdapter implements IStudentInstitutionPersistencePort {

    private final IStudentInstitutionRepository studentInstitutionRepository;
    private final IStudentInstitutionEntityMapper studentInstitutionEntityMapper;

    @Override
    public void save(StudentInstitution studentInstitution) {
        studentInstitutionRepository.save(studentInstitutionEntityMapper.toEntity(studentInstitution));
    }
}
