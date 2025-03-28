package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.City;

import java.util.Optional;

public interface ICityPersistencePort {

    Optional<City> findById(Long cityId);
}
