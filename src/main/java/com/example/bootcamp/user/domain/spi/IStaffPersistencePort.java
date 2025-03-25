package com.example.bootcamp.user.domain.spi;

import com.example.bootcamp.user.domain.model.Staff;

public interface IStaffPersistencePort {

    void save(Staff staff);
    Staff findByEmail(String emailMember);
}
