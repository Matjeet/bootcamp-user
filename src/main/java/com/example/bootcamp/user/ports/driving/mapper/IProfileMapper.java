package com.example.bootcamp.user.ports.driving.mapper;

import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.ports.driving.dto.request.CreateProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProfileMapper {

    @Mapping(source = "badges", target = "badges", qualifiedByName = "mapLongToBadgesList")
    @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "mapLongToHobbiesList")
    Profile toModel(CreateProfileDto createProfileDto);

    @Named("mapLongToBadgesList")
    static List<Badge> mapLongToBadgesList(List<Long> badgesId){
        return badgesId != null ? badgesId.stream().map(badgeId -> new Badge(badgeId, null)).toList() : null;
    }

    @Named("mapLongToHobbiesList")
    static List<Hobby> mapLongToHobbiesList(List<Long> hobbiesId){
        return hobbiesId != null ? hobbiesId.stream().map(hobbyId -> new Hobby(hobbyId, null)).toList() : null;
    }
}
