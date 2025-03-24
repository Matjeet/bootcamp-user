package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Institution;

public interface IInstitutionPersistencePort {
    Institution findById(Long institutionId);
}
