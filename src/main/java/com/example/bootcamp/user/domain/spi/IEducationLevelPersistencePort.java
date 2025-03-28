package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.EducationLevel;

import java.util.Optional;

public interface IEducationLevelPersistencePort {
    Optional<EducationLevel> findByName(String educationLevelName);
}
