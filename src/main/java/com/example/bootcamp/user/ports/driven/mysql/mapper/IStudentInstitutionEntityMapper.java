package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driven.mysql.entity.StudentInstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStudentInstitutionEntityMapper {

    StudentInstitutionEntity toEntity(StudentInstitution studentInstitution);
}
