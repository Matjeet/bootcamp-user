package com.example.bootcamp.user.configuration.beans;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.api.IStaffServicePort;
import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.api.usecase.ProfileUseCase;
import com.example.bootcamp.user.domain.api.usecase.StaffUseCase;
import com.example.bootcamp.user.domain.api.usecase.StudentUseCase;
import com.example.bootcamp.user.domain.spi.*;
import com.example.bootcamp.user.ports.driven.dynamodb.adapter.ProfileAdapter;
import com.example.bootcamp.user.ports.driven.dynamodb.mapper.IProfileEntityMapper;
import com.example.bootcamp.user.ports.driven.dynamodb.repository.IProfileRepository;
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
    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;
    private final IStaffRepository staffRepository;
    private final IStaffEntityMapper staffEntityMapper;
    private final IStaffRolRepository staffRolRepository;
    private final IStaffRolEntityMapper staffRolEntityMapper;
    private final IProfileRepository profileRepository;
    private final IProfileEntityMapper profileEntityMapper;
    private final IBadgeRepository badgeRepository;
    private final IBadgeEntityMapper badgeEntityMapper;
    private final IHobbyRepository hobbyRepository;
    private final IHobbyEntityMapper hobbyEntityMapper;

    @Bean
    public IStudentServicePort studentServicePort(){
        return new StudentUseCase(
                studentPersistencePort(),
                institutionPersistencePort(),
                educationLevelPersistencePort(),
                developerRolPersistencePort(),
                sourcePersistencePort(),
                cityPersistencePort(),
                studentInstitutionPersistencePort()
        );
    }

    @Bean
    public IStaffServicePort staffServicePort(){
        return new StaffUseCase(
                staffPersistencePort(),
                developerRolPersistencePort(),
                staffRolPersistencePort(),
                studentPersistencePort()
        );
    }

    @Bean
    public IProfileServicePort profileServicePort(){
        return new ProfileUseCase(
                profilePersistencePort(),
                studentPersistencePort(),
                staffPersistencePort(),
                badgesPersistencePort(),
                hobbyPersistencePort(),
                developerRolPersistencePort(),
                staffRolPersistencePort()
        );
    }

    @Bean
    public IStudentPersistencePort studentPersistencePort(){
        return new StudentAdapter(
                studentRepository,
                studentEntityMapper
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

    @Bean
    public ICityPersistencePort cityPersistencePort(){
        return new CityAdapter(
                cityRepository,
                cityEntityMapper
        );
    }

    @Bean
    public IStudentInstitutionPersistencePort studentInstitutionPersistencePort(){
        return new StudentInstitutionAdapter(
                studentInstitutionRepository,
                studentInstitutionEntityMapper
        );
    }

    @Bean
    public IStaffPersistencePort staffPersistencePort(){
        return new StaffAdapter(
                staffRepository,
                staffEntityMapper
        );
    }

    @Bean
    public IStaffRolPersistencePort staffRolPersistencePort(){
        return new StaffRolAdapter(
                staffRolRepository,
                staffRolEntityMapper
        );
    }

    @Bean
    public IProfilePersistencePort profilePersistencePort(){
        return new ProfileAdapter(
              profileRepository,
              profileEntityMapper
        );
    }

    @Bean
    public IBadgesPersistencePort badgesPersistencePort(){
        return new BadgeAdapter(
                badgeRepository,
                badgeEntityMapper
        );
    }

    @Bean
    public IHobbyPersistencePort hobbyPersistencePort(){
        return new HobbyAdapter(
                hobbyRepository,
                hobbyEntityMapper
        );
    }
}
