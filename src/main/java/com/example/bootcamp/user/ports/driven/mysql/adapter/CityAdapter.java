package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.City;
import com.example.bootcamp.user.domain.spi.ICityPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.ICityEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.ICityRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CityAdapter implements ICityPersistencePort {

    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;

    @Override
    public Optional<City> findById(Long cityId) {
        return cityRepository.findById(cityId).map(cityEntityMapper::toModel);
    }
}
