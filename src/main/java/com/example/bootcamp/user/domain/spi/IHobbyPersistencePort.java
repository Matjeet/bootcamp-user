package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Hobby;

import java.util.List;

public interface IHobbyPersistencePort {

    List<Hobby> findAllById(List<Hobby> hobbiesIdList);
}
