package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Student;

public interface IStudentPersistencePort {
    Student findByEmailOrIdentification(String email, String identification);
}
