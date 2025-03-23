package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.DeveloperRol;
import com.example.bootcamp.user.ports.driven.mysql.entity.DeveloperRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDeveloperRolEntityMapper {

    DeveloperRol toModel(DeveloperRolEntity developerRolEntity);
}
