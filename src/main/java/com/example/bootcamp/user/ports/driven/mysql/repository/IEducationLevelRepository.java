package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.EducationLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEducationLevelRepository extends JpaRepository<EducationLevelEntity, Long> {

    Optional<EducationLevelEntity> findByName(String educationLevelName);
}
