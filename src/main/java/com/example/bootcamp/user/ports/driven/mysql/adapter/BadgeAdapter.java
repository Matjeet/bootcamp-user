package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.spi.IBadgesPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.entity.BadgeEntity;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IBadgeEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IBadgeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BadgeAdapter implements IBadgesPersistencePort {

    private final IBadgeRepository badgeRepository;
    private final IBadgeEntityMapper badgeEntityMapper;

    @Override
    public List<Badge> findAllBadges(List<Badge> listBadgesId) {
        List<BadgeEntity> badgeEntityList = badgeRepository.findAllById(badgeEntityMapper.toLongList(listBadgesId));
        return badgeEntityMapper.toModelList(badgeEntityList);
    }
}
