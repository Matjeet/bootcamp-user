package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;

public interface IStudentPersistencePort {
    void save (Student student, StudentInstitution studentInstitution);
    Student findByEmailOrIdentification(String email, String identification);
}
