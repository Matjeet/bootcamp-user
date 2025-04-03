package com.example.bootcamp.user.ports.driven.dynamodb.repository;

import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;

import java.util.Optional;

public interface IProfileRepository {
    ProfileEntity save(ProfileEntity profileEntity);

    Optional<ProfileEntity> findByEmail(String email);
}
