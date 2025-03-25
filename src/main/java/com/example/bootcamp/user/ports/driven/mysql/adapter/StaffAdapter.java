package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStaffEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStaffRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaffAdapter implements IStaffPersistencePort {

    private final IStaffRepository staffRepository;
    private final IStaffEntityMapper staffEntityMapper;

    @Override
    public void save(Staff staff) {
        staffRepository.save(staffEntityMapper.toEntity(staff));
    }

    @Override
    public Staff findByEmail(String emailMember) {
        return staffEntityMapper.toModel(staffRepository.findByEmail(emailMember).orElse(null));
    }
}
