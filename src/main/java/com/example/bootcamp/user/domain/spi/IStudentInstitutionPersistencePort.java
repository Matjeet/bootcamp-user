package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.StudentInstitution;

public interface IStudentInstitutionPersistencePort {

    void save(StudentInstitution studentInstitution);
}
