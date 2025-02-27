package com.example.bootcamp.user.ports.driven.mysql.repository;

import com.example.bootcamp.user.ports.driven.mysql.entity.StudentInstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentInstitutionRepository extends JpaRepository<StudentInstitutionEntity, Long> {
}
