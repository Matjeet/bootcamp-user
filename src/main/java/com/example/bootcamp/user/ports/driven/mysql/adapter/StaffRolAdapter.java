package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.StaffRol;
import com.example.bootcamp.user.domain.spi.IStaffRolPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStaffRolEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStaffRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaffRolAdapter implements IStaffRolPersistencePort {

    private final IStaffRolRepository staffRolRepository;
    private final IStaffRolEntityMapper staffRolEntityMapper;

    @Override
    public StaffRol findByName(String staffRolName) {
        return staffRolEntityMapper.toModel(staffRolRepository.findByName(staffRolName).orElse(null));
    }
}
