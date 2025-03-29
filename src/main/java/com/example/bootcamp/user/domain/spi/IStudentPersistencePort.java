package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Student;

import java.util.Optional;

public interface IStudentPersistencePort {
    Optional<Student> findByEmailOrIdentification(String email, String identification);

    Optional<Student> findById(Long studentId);
}
