package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.StaffRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStaffRolRepository extends JpaRepository<StaffRolEntity, Long> {

    Optional<StaffRolEntity> findByName(String staffRolName);
}
