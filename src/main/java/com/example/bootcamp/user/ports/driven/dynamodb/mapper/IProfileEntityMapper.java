package com.example.bootcamp.user.ports.driven.dynamodb.mapper;

import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.ports.driven.dynamodb.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProfileEntityMapper {

    @Mapping(source = "badges", target = "badges", qualifiedByName = "mapBadgesToStringList")
    @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "mapHobbiesToStringList")
    ProfileEntity toEntity(Profile profile);

    @Named("mapBadgesToStringList")
    static List<String> mapBadgesToStringList(List<Badge> badges) {
        return badges != null ? badges.stream().map(Badge::getName).toList() : null;
    }

    @Named("mapHobbiesToStringList")
    static List<String> mapHobbiesToStringList(List<Hobby> hobbies) {
        return hobbies != null ? hobbies.stream().map(Hobby::getName).toList() : null;
    }
}
