package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.*;
import com.example.bootcamp.user.domain.spi.*;

import java.util.Optional;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.Validations.*;
import static java.util.Objects.isNull;

public class StudentUseCase implements IStudentServicePort {

    private final IStudentPersistencePort studentPersistencePort;
    private final IInstitutionPersistencePort institutionPersistencePort;
    private final IEducationLevelPersistencePort educationLevelPersistencePort;
    private final IDeveloperRolPersistencePort developerRolPersistencePort;
    private final ISourcePersistencePort courseDiscoverySourcePersistencePort;
    private final ICityPersistencePort cityPersistencePort;
    private final IStudentInstitutionPersistencePort studentInstitutionPersistencePort;

    public StudentUseCase(
            IStudentPersistencePort studentPersistencePort,
            IInstitutionPersistencePort institutionPersistencePort,
            IEducationLevelPersistencePort educationLevelPersistencePort,
            IDeveloperRolPersistencePort developerRolPersistencePort,
            ISourcePersistencePort courseDiscoverySourcePersistencePort,
            ICityPersistencePort cityPersistencePort,
            IStudentInstitutionPersistencePort studentInstitutionPersistencePort
    ){
        this.studentPersistencePort = studentPersistencePort;
        this.institutionPersistencePort = institutionPersistencePort;
        this.educationLevelPersistencePort = educationLevelPersistencePort;
        this.developerRolPersistencePort = developerRolPersistencePort;
        this.courseDiscoverySourcePersistencePort = courseDiscoverySourcePersistencePort;
        this.cityPersistencePort = cityPersistencePort;
        this.studentInstitutionPersistencePort = studentInstitutionPersistencePort;
    }

    @Override
    public void save(Student student, StudentInstitution studentInstitution) {

        if(!isValidEmail(student.getEmail())) throw new InvalidEmailException(EMAIL_INVALID_MESSAGE);
        if(!isValidCellphone(student.getCellphone())) throw new InvalidCellphoneException(CELLPHONE_INVALID_MESSAGE);
        if(!isValidIdentification(student.getIdentification())) throw new InvalidIdentificationException(IDENTIFICATION_INVALID_MESSAGE);
        if(!isAdult(student.getBirthDate())) throw new InvalidAgeException(STUDENT_IS_NOT_AN_ADULT);

        if(studentPersistencePort.findByEmailOrIdentification(student.getEmail(), student.getIdentification()).isPresent())
            throw new StudentExistsException(STUDENT_EXIST);

        if(institutionPersistencePort.findById(studentInstitution.getInstitution().getId()).isEmpty())
            throw new InstitutionNotFoundException(INSTITUTION_NOT_FOUND);

        Optional<EducationLevel> educationLevel = educationLevelPersistencePort.findByName(student.getEducationLevel().getName());
        if(educationLevel.isEmpty()) throw new EducationLevelNotFoundException(EDUCATION_LEVEL_NOT_FOUND);

        Optional<DeveloperRol> developerRol = developerRolPersistencePort.findByName(student.getDeveloperRol().getName());
        if(developerRol.isEmpty()) throw new DeveloperRolNotFoundException(DEVELOPER_ROL_NOT_FOUND);

        Optional<Source> courseDiscoverySource = courseDiscoverySourcePersistencePort.findByName(student.getCourseDiscoverySource().getName());
        if(courseDiscoverySource.isEmpty()) throw new SourceNotFoundException(SOURCE_NOT_FOUND);

        if(cityPersistencePort.findById(student.getLocation().getCityId()).isEmpty()) throw new CityNotFoundException(CITY_NOT_FOUND);

        student.setEducationLevel(educationLevel.get());
        student.setDeveloperRol(developerRol.get());
        student.setCourseDiscoverySource(courseDiscoverySource.get());

        studentInstitution.setInstitutionDetail(
                isNull(studentInstitution.getInstitutionDetail()) ?
                        studentInstitution.getInstitutionDetail() :
                        studentInstitution.getInstitutionDetail().trim()
                );

        studentInstitution.setStudent(student);

        studentInstitutionPersistencePort.save(studentInstitution);
    }
}
