package com.example.bootcamp.user.ports.driving.mapper;

import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.ports.driving.dto.request.StaffRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStaffMapper {

    @Mapping(target = "developerRol.name", source = "developerRol")
    @Mapping(target = "staffRol.name", source = "staffRol")
    Staff toModel(StaffRegisterDto staffRegisterDto);
}
