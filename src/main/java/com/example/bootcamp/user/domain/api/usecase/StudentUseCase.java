package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.exception.StudentExistsException;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;

import static com.example.bootcamp.user.domain.util.StudentMessage.STUDENT_EXIST;
import static java.util.Objects.isNull;

public class StudentUseCase implements IStudentServicePort {

    private final IStudentPersistencePort studentPersistencePort;

    public StudentUseCase(IStudentPersistencePort studentPersistencePort){
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    public void save(Student student, StudentInstitution studentInstitution) {

        if(!isNull(studentPersistencePort.findByEmailOrIdentification(student.getEmail(), student.getIdentification()))){
            throw new StudentExistsException(STUDENT_EXIST);
        }

        studentPersistencePort.save(student, studentInstitution);
    }
}
