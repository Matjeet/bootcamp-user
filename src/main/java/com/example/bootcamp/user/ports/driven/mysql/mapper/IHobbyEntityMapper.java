package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.ports.driven.mysql.entity.HobbyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IHobbyEntityMapper {

    List<HobbyEntity> toListEntity(List<Hobby> hobbyList);

    List<Hobby> toListModel(List<HobbyEntity> hobbyEntityList);
}
