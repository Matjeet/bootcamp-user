package com.example.bootcamp.user.configuration.beans;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.api.usecase.StudentUseCase;
import com.example.bootcamp.user.domain.spi.*;
import com.example.bootcamp.user.ports.driven.mysql.adapter.*;
import com.example.bootcamp.user.ports.driven.mysql.mapper.*;
import com.example.bootcamp.user.ports.driven.mysql.repository.*;
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
    private final IEducationLevelRepository educationLevelRepository;
    private final IEducationLevelEntityMapper educationLevelEntityMapper;
    private final IDeveloperRolRepository developerRolRepository;
    private final IDeveloperRolEntityMapper developerRolEntityMapper;
    private final ISourceRepository sourceRepository;
    private final ISourceEntityMapper sourceEntityMapper;

    @Bean
    public IStudentServicePort studentServicePort(){
        return new StudentUseCase(
                studentPersistencePort(),
                institutionPersistencePort(),
                educationLevelPersistencePort(),
                developerRolPersistencePort(),
                sourcePersistencePort()
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

    @Bean
    public IEducationLevelPersistencePort educationLevelPersistencePort(){
        return new EducationLevelAdapter(
                educationLevelRepository,
                educationLevelEntityMapper
        );
    }

    @Bean
    public IDeveloperRolPersistencePort developerRolPersistencePort(){
        return new DeveloperRolAdapter(
                developerRolRepository,
                developerRolEntityMapper
        );
    }

    @Bean
    public ISourcePersistencePort sourcePersistencePort(){
        return new SourceAdapter(
                sourceRepository,
                sourceEntityMapper
        );
    }

}
