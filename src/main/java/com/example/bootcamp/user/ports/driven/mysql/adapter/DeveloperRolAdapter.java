package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.DeveloperRol;
import com.example.bootcamp.user.domain.spi.IDeveloperRolPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IDeveloperRolEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IDeveloperRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeveloperRolAdapter implements IDeveloperRolPersistencePort {

    private final IDeveloperRolRepository developerRolRepository;
    private final IDeveloperRolEntityMapper developerRolEntityMapper;

    @Override
    public DeveloperRol findByName(String developerRolName) {
        return developerRolEntityMapper.toModel(developerRolRepository.findByName(developerRolName).orElse(null));
    }
}
