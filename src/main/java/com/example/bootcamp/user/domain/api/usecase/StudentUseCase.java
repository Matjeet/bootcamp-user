package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.*;
import com.example.bootcamp.user.domain.spi.*;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.Validations.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

        if(nonNull(studentPersistencePort.findByEmailOrIdentification(student.getEmail(), student.getIdentification())))
            throw new StudentExistsException(STUDENT_EXIST);

        if(isNull(institutionPersistencePort.findById(studentInstitution.getInstitution().getId())))
            throw new InstitutionNotFoundException(INSTITUTION_NOT_FOUND);

        EducationLevel educationLevel = educationLevelPersistencePort.findByName(student.getEducationLevel().getName());
        if(isNull(educationLevel)) throw new EducationLevelNotFoundException(EDUCATION_LEVEL_NOT_FOUND);

        DeveloperRol developerRol = developerRolPersistencePort.findByName(student.getDeveloperRol().getName());
        if(isNull(developerRol)) throw new DeveloperRolNotFoundException(DEVELOPER_ROL_NOT_FOUND);

        Source courseDiscoverySource = courseDiscoverySourcePersistencePort.findByName(student.getCourseDiscoverySource().getName());
        if(isNull(courseDiscoverySource)) throw new SourceNotFoundException(SOURCE_NOT_FOUND);

        if(isNull(cityPersistencePort.findById(student.getLocation().getCityId()))) throw new CityNotFoundException(CITY_NOT_FOUND);

        student.setEducationLevel(educationLevel);
        student.setDeveloperRol(developerRol);
        student.setCourseDiscoverySource(courseDiscoverySource);

        studentInstitution.setStudent(student);

        studentInstitutionPersistencePort.save(studentInstitution);
    }
}
