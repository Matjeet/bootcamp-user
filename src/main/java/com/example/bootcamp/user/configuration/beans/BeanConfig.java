package com.example.bootcamp.user.configuration.beans;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.api.usecase.StudentUseCase;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.adapter.StudentAdapter;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentInstitutionRepository;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final IStudentRepository studentRepository;
    private final IStudentInstitutionRepository studentInstitutionRepository;
    private final IStudentEntityMapper studentEntityMapper;
    private final IStudentInstitutionEntityMapper studentInstitutionEntityMapper;

    @Bean
    public IStudentServicePort studentServicePort(){
        return new StudentUseCase(studentPersistencePort());
    }

    @Bean
    public IStudentPersistencePort studentPersistencePort(){
        return new StudentAdapter(
                studentRepository,
                studentEntityMapper,
                studentInstitutionRepository,
                studentInstitutionEntityMapper
        );
    }

}
