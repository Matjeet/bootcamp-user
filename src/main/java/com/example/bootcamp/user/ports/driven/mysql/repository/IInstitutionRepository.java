package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInstitutionRepository extends JpaRepository<InstitutionEntity, Long> {
}
