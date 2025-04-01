package com.example.bootcamp.user.ports.driven.mysql.adapter;

import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.ports.driven.mysql.mapper.IStaffEntityMapper;
import com.example.bootcamp.user.ports.driven.mysql.repository.IStaffRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class StaffAdapter implements IStaffPersistencePort {

    private final IStaffRepository staffRepository;
    private final IStaffEntityMapper staffEntityMapper;

    @Override
    public void save(Staff staff) {
        staffRepository.save(staffEntityMapper.toEntity(staff));
    }

    @Override
    public Optional<Staff> findByEmail(String emailMember) {
        return staffEntityMapper.toOptionalModel(staffRepository.findByEmail(emailMember));
    }

    @Override
    public Optional<Staff> findById(Long memberId) {
        return staffRepository.findById(memberId).map(staffEntityMapper::toModel);
    }
}
