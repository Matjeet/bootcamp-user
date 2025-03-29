package com.example.bootcamp.user.ports.driven.mysql.mapper;

import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.ports.driven.mysql.entity.BadgeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBadgeEntityMapper {

    List<BadgeEntity> toEntityList(List<Badge> badgeList);

    List<Badge> toModelList(List<BadgeEntity> badgeEntityList);
}
