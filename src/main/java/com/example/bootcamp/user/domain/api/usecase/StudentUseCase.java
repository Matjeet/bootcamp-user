package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.EducationLevel;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.domain.spi.IEducationLevelPersistencePort;
import com.example.bootcamp.user.domain.spi.IInstitutionPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.Validations.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class StudentUseCase implements IStudentServicePort {

    private final IStudentPersistencePort studentPersistencePort;
    private final IInstitutionPersistencePort institutionPersistencePort;
    private final IEducationLevelPersistencePort educationLevelPersistencePort;

    public StudentUseCase(
            IStudentPersistencePort studentPersistencePort,
            IInstitutionPersistencePort institutionPersistencePort,
            IEducationLevelPersistencePort educationLevelPersistencePort
    ){
        this.studentPersistencePort = studentPersistencePort;
        this.institutionPersistencePort = institutionPersistencePort;
        this.educationLevelPersistencePort = educationLevelPersistencePort;
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

        student.setEducationLevel(educationLevel);

        studentPersistencePort.save(student, studentInstitution);
    }
}
