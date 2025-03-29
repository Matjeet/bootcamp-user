package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBadgeRepository extends JpaRepository<BadgeEntity, Long> {

    List<BadgeEntity> findAllById(List<BadgeEntity> badgeEntityList);
}
