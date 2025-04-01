package com.example.bootcamp.user.ports.driven.dynamodb.adapter;

import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.IProfilePersistencePort;
import com.example.bootcamp.user.ports.driven.dynamodb.mapper.IProfileEntityMapper;
import com.example.bootcamp.user.ports.driven.dynamodb.repository.IProfileRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileAdapter implements IProfilePersistencePort {

    private final IProfileRepository profileRepository;
    private final IProfileEntityMapper profileEntityMapper;

    @Override
    public void save(Profile profile) {
        profileRepository.save(profileEntityMapper.toEntity(profile));
    }
}
