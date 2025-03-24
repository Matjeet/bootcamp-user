package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Source;
import com.example.bootcamp.user.domain.spi.ISourcePersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.ISourceEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.ISourceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceAdapter implements ISourcePersistencePort {

    private final ISourceRepository sourceRepository;
    private final ISourceEntityMapper sourceEntityMapper;

    @Override
    public Source findByName(String sourceName) {
        return sourceEntityMapper.toModel(sourceRepository.findByName(sourceName).orElse(null));
    }
}
