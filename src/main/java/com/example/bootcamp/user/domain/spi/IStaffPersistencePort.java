package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Staff;

import java.util.Optional;

public interface IStaffPersistencePort {

    void save(Staff staff);

    Optional<Staff> findByEmail(String emailMember);

    Optional<Staff> findById(Long memberId);
}
