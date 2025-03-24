package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.DeveloperRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDeveloperRolRepository extends JpaRepository<DeveloperRolEntity, Long> {

    Optional<DeveloperRolEntity> findByName(String developerRolName);
}
