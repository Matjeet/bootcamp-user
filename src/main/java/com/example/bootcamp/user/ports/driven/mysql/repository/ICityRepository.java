package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
}
