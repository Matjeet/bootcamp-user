package com.example.bootcamp.user.ports.driven.dynamodb.repository;

import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;

public interface IProfileRepository {
    void save(ProfileEntity profileEntity);
}
