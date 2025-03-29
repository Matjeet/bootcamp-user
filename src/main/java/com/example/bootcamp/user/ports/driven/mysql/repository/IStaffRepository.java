package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<StaffEntity, Long> {

    StaffEntity findByEmail(String emailMember);
}
