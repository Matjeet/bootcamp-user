package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Profile;

public interface IProfilePersistencePort {

    void save(Profile profile);
}
