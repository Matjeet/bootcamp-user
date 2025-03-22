package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.EducationLevel;

public interface IEducationLevelPersistencePort {
    EducationLevel findByName(String educationLevelName);
}
