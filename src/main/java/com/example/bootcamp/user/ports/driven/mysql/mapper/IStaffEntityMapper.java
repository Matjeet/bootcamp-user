package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.ports.driven.mysql.entity.StaffEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStaffEntityMapper {

    StaffEntity toEntity(Staff staff);

    Staff toModel(StaffEntity staffEntity);
}
