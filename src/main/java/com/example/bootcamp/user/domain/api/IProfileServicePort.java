package com.example.bootcamp.user.domain.api;

import com.example.bootcamp.user.domain.model.Profile;

public interface IProfileServicePort {

    void save(Profile profile);
}
