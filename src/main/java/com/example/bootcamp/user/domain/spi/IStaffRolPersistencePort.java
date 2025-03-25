package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.StaffRol;

public interface IStaffRolPersistencePort {

    StaffRol findByName(String staffRolName);
}
