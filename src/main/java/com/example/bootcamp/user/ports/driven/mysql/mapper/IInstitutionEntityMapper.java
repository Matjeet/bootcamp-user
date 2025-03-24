package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Institution;
import com.example.bootcamp.user.ports.driven.mysql.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInstitutionEntityMapper {

    Institution toOptionalModel(InstitutionEntity institutionEntity);
}
