package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.StaffRol;

import java.util.Optional;

public interface IStaffRolPersistencePort {

    Optional<StaffRol> findByName(String staffRolName);
}
