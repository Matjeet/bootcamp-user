package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.ports.driven.mysql.entity.HobbyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IHobbyEntityMapper {

    default List<Long> toLongList(List<Hobby> hobbyList){
        return isNull(hobbyList) ? null : hobbyList.stream()
                .map(Hobby::getId)
                .toList();
    }

    List<Hobby> toListModel(List<HobbyEntity> hobbyEntityList);
}
