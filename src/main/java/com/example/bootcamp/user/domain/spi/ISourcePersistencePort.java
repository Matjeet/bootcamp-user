package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Source;

public interface ISourcePersistencePort {

    Source findByName(String sourceName);
}
