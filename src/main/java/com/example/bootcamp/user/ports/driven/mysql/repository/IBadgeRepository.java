package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBadgeRepository extends JpaRepository<BadgeEntity, Long> {

    @Query(value = "SELECT b FROM BadgeEntity b WHERE b.id IN (:badgeEntityList)")
    List<BadgeEntity> findAllById(List<BadgeEntity> badgeEntityList);
}
