package com.example.bootcamp.user.ports.driven.dynamodb.adapter;

import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.IProfilePersistencePort;
import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;
import com.example.bootcamp.user.ports.driven.dynamodb.mapper.IProfileEntityMapper;
import com.example.bootcamp.user.ports.driven.dynamodb.repository.IProfileRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProfileAdapter implements IProfilePersistencePort {

    private final IProfileRepository profileRepository;
    private final IProfileEntityMapper profileEntityMapper;

    @Override
    public Optional<Profile> save(Profile profile) {
        return profileEntityMapper.toOptionalModel(profileRepository.save(profileEntityMapper.toEntity(profile)));
    }

    @Override
    public Optional<Profile> getByEmail(String email) {
        return profileEntityMapper.toOptionalModel(profileRepository.findByEmail(email).orElseGet(ProfileEntity::new));
    }

    @Override
    public void delete(String email) {
        profileRepository.delete(email);
    }
}
