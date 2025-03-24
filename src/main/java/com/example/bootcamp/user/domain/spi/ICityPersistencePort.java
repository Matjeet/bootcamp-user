package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.City;

public interface ICityPersistencePort {

    City findById(Long cityId);
}
