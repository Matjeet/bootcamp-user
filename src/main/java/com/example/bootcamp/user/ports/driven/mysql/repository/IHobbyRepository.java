package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHobbyRepository extends JpaRepository<HobbyEntity, Long> {

    @Query(value = "SELECT h FROM HobbyEntity h WHERE h.id IN (:hobbiesIdList)")
    List<HobbyEntity> findAllById(List<HobbyEntity> hobbiesIdList);
}
