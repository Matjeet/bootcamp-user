package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Institution;

import java.util.Optional;

public interface IInstitutionPersistencePort {
    Optional<Institution> findById(Long institutionId);
}
