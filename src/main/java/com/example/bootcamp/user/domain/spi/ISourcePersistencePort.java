package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Source;

import java.util.Optional;

public interface ISourcePersistencePort {

    Optional<Source> findByName(String sourceName);
}
