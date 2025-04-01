package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static com.example.bootcamp.user.domain.util.StudentConstants.EMPTY_STRING;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.Validations.isValidDescription;
import static com.example.bootcamp.user.domain.util.Validations.isValidUrl;
import static java.util.Objects.nonNull;

public class ProfileUseCase implements IProfileServicePort {

    private final IProfilePersistencePort profilePersistencePort;
    private final IStudentPersistencePort studentPersistencePort;
    private final IStaffPersistencePort staffPersistencePort;
    private final IBadgesPersistencePort badgesPersistencePort;
    private final IHobbyPersistencePort hobbyPersistencePort;
    private final IDeveloperRolPersistencePort developerRolPersistencePort;
    private final IStaffRolPersistencePort staffRolPersistencePort;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileUseCase.class);

    public ProfileUseCase(
            IProfilePersistencePort profilePersistencePort,
            IStudentPersistencePort studentPersistencePort,
            IStaffPersistencePort staffPersistencePort,
            IBadgesPersistencePort badgesPersistencePort,
            IHobbyPersistencePort hobbyPersistencePort,
            IDeveloperRolPersistencePort developerRolPersistencePort,
            IStaffRolPersistencePort staffRolPersistencePort
    ){
        this.profilePersistencePort = profilePersistencePort;
        this.studentPersistencePort = studentPersistencePort;
        this.staffPersistencePort = staffPersistencePort;
        this.badgesPersistencePort = badgesPersistencePort;
        this.hobbyPersistencePort = hobbyPersistencePort;
        this.developerRolPersistencePort = developerRolPersistencePort;
        this.staffRolPersistencePort = staffRolPersistencePort;
    }

    /**
     * TODO: validar que el perfil no exista
     * TODO: validar que si la persona es un estudiante no se envíe un StaffRol
     */
    @Override
    public void save(Profile profile) {

        Optional.ofNullable(profile.getUserEmail())
                .filter(userEmail -> studentPersistencePort.findByEmailOrIdentification(userEmail, EMPTY_STRING).isPresent()
                        || staffPersistencePort.findByEmail(userEmail).isPresent())
                .ifPresentOrElse(
                        userId -> {},
                        () -> {throw new UserNotFoundException(USER_NOT_FOUND);}
                );

        List<Badge> badgeList =  Optional.ofNullable(profile.getBadges())
                .map(badgesPersistencePort::findAllBadges)
                .filter(list ->{
                    if(list.isEmpty()) throw new BadgesNotFoundException(BADGES_NOT_FOUND);
                    return true;
                })
                .orElse(null);

        if(nonNull(badgeList) && badgeList.size() != profile.getBadges().size()) {
            List<Long> badgeListId = badgeList.stream().map(Badge::getId).toList();
            List<Long> badgesNotFound = profile.getBadges().stream()
                    .map(Badge::getId)
                    .filter(badge -> !badgeListId.contains(badge)).toList();
            LOGGER.error(MISSING_INFO + SOME_BADGES_NOT_FOUND, profile.getUserEmail(), badgesNotFound);
        }

        List<Hobby> hobbyList = Optional.ofNullable(profile.getHobbies())
                        .map(hobbyPersistencePort::findAllById)
                        .filter(list -> {
                            if(list.isEmpty()) throw new HobbiesNotFoundException(HOBBIES_NOT_FOUND);
                            return true;
                        })
                        .orElse(null);

        if(nonNull(hobbyList) && hobbyList.size() != profile.getHobbies().size()){
            List<Long> hobbiesListId = hobbyList.stream().map(Hobby::getId).toList();
            List<Long> hobbiesNotFound = profile.getHobbies().stream()
                    .map(Hobby::getId)
                    .filter(hobby -> !hobbiesListId.contains(hobby)).toList();
            LOGGER.error(MISSING_INFO + SOME_HOBBIES_NOT_FOUND, profile.getUserEmail(), hobbiesNotFound);
        }

        Optional.ofNullable(profile.getDeveloperRol())
                .filter(developerRol -> developerRolPersistencePort.findByName(developerRol).isPresent())
                .ifPresentOrElse(
                        developerRol -> {},
                        () -> {throw new DeveloperRolNotFoundException(DEVELOPER_ROL_NOT_FOUND);}
                );

        Optional.ofNullable(profile.getStaffRol())
                .filter(staffRol -> staffRolPersistencePort.findByName(staffRol).isEmpty())
                .ifPresent(staffRol -> {
                    throw new StaffRolNotFoundException(STAFF_ROL_NOT_FOUND);
                });

        if(!isValidDescription(profile.getDescription()))
            throw new DescriptionTooLongException(DESCRIPTION_TOO_LONG_MESSAGE);

        Optional.ofNullable(profile.getSocialMedia())
                        .ifPresent(socialMedia -> socialMedia.forEach((media, url) -> {
                            if(!isValidUrl(url)) throw new InvalidUrlException(URL_INVALID_MESSAGE);
                        }));

        profile.setBadges(badgeList);
        profile.setHobbies(hobbyList);

        this.profilePersistencePort.save(profile);
    }
}
