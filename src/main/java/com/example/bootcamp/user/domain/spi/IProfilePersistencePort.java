package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Profile;

import java.util.Optional;

public interface IProfilePersistencePort {

    Optional<Profile> save(Profile profile);

    Optional<Profile> getByEmail(String email);
}
