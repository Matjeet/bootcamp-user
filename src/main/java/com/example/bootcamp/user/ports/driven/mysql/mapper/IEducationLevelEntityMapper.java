package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.EducationLevel;
import com.example.bootcamp.user.ports.driven.mysql.entity.EducationLevelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEducationLevelEntityMapper {

    EducationLevel toModel(EducationLevelEntity educationLevelEntity);
}
