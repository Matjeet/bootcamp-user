package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Institution;
import com.example.bootcamp.user.domain.spi.IInstitutionPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IInstitutionEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IInstitutionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstitutionAdapter implements IInstitutionPersistencePort {

    private final IInstitutionRepository institutionRepository;
    private final IInstitutionEntityMapper institutionEntityMapper;

    @Override
    public Institution findById(Long institutionId) {
        return institutionEntityMapper.toOptionalModel(institutionRepository.findById(institutionId).orElse(null));
    }
}
