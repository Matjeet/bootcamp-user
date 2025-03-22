package com.example.bootcamp.user.configuration.beans;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.api.usecase.StudentUseCase;
import com.example.bootcamp.user.domain.spi.IInstitutionPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.adapter.InstitutionAdapter;
import com.example.bootcamp.user.ports.driven.mysql.adapter.StudentAdapter;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStudentInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IInstitutionRepository;
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
    private final IInstitutionRepository institutionRepository;
    private final IInstitutionEntityMapper institutionEntityMapper;

    @Bean
    public IStudentServicePort studentServicePort(){
        return new StudentUseCase(
                studentPersistencePort(),
                institutionPersistencePort()
        );
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

    @Bean
    public IInstitutionPersistencePort institutionPersistencePort(){
        return new InstitutionAdapter(
                institutionRepository,
                institutionEntityMapper
        );
    }

}
