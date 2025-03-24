package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISourceRepository extends JpaRepository<SourceEntity, Long> {

    Optional<SourceEntity> findByName(String sourceName);
}
