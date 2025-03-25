package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.StaffRol;
import com.example.bootcamp.user.ports.driven.mysql.entity.StaffRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStaffRolEntityMapper {

    StaffRol toModel(StaffRolEntity staffRolEntity);
}
