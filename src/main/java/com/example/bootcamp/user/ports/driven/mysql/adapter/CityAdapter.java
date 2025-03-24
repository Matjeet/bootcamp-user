package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.City;
import com.example.bootcamp.user.domain.spi.ICityPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.ICityEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.ICityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CityAdapter implements ICityPersistencePort {

    private final ICityRepository cityRepository;
    private final ICityEntityMapper cityEntityMapper;

    @Override
    public City findById(Long cityId) {
        return cityEntityMapper.toModel(cityRepository.findById(cityId).orElse(null));
    }
}
