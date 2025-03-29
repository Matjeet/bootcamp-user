package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.HobbyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHobbyRepository extends JpaRepository<HobbyEntity, Long> {

    List<HobbyEntity> findAllById(List<HobbyEntity> hobbiesIdList);
}
