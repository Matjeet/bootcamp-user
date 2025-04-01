package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.ports.driven.mysql.entity.BadgeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBadgeEntityMapper {

    default List<Long> toLongList(List<Badge> badgeList){
        return isNull(badgeList) ? null : badgeList.stream()
                .map(Badge::getId)
                .toList();
    }

    List<Badge> toModelList(List<BadgeEntity> badgeEntityList);
}
