package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.domain.spi.IHobbyPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.entity.HobbyEntity;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IHobbyEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IHobbyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HobbyAdapter implements IHobbyPersistencePort {

    private final IHobbyRepository hobbyRepository;
    private final IHobbyEntityMapper hobbyEntityMapper;

    @Override
    public List<Hobby> findAllById(List<Hobby> hobbiesIdList) {
        List<HobbyEntity> hobbyEntityList = hobbyRepository.findAllById(hobbyEntityMapper.toLongList(hobbiesIdList));
        return hobbyEntityMapper.toListModel(hobbyEntityList);
    }
}
