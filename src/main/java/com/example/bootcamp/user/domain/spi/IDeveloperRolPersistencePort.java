package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.DeveloperRol;

import java.util.Optional;

public interface IDeveloperRolPersistencePort {
    Optional<DeveloperRol> findByName(String developerRolName);
}
