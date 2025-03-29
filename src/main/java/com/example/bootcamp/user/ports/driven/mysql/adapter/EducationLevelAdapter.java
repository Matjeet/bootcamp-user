package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.EducationLevel;
import com.example.bootcamp.user.domain.spi.IEducationLevelPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IEducationLevelEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IEducationLevelRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class EducationLevelAdapter implements IEducationLevelPersistencePort {

    private final IEducationLevelRepository educationLevelRepository;
    private final IEducationLevelEntityMapper educationLevelEntityMapper;

    @Override
    public Optional<EducationLevel> findByName(String educationLevelName) {
        return educationLevelRepository.findByName(educationLevelName).map(educationLevelEntityMapper::toModel);
    }
}
