package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Badge;

import java.util.List;

public interface IBadgesPersistencePort {
    List<Badge> findAllBadges(List<Badge> listBadgesId);
}
