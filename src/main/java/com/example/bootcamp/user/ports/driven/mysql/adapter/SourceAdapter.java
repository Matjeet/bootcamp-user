package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Source;
import com.example.bootcamp.user.domain.spi.ISourcePersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.ISourceEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.ISourceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SourceAdapter implements ISourcePersistencePort {

    private final ISourceRepository sourceRepository;
    private final ISourceEntityMapper sourceEntityMapper;

    @Override
    public Optional<Source> findByName(String sourceName) {
        return sourceRepository.findByName(sourceName).map(sourceEntityMapper::toModel);
    }
}
