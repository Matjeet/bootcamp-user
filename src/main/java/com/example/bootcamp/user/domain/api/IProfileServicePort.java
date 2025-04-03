package com.example.bootcamp.user.domain.api;

import com.example.bootcamp.user.domain.model.Profile;

public interface IProfileServicePort {

    void save(Profile profile);

    Profile getByEmail(String email);

    Profile update(String email, Profile profile);

    void delete(String email);
}
