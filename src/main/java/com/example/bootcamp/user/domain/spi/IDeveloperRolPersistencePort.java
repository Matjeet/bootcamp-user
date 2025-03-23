package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.DeveloperRol;

public interface IDeveloperRolPersistencePort {
    DeveloperRol findByName(String developerRolName);
}
