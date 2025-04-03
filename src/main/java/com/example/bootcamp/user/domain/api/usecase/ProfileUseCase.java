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
import static com.example.bootcamp.user.domain.util.Validations.*;
import static java.util.Objects.isNull;

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
     * TODO: dejar de usar ID en los HOBBIES y los BADGES. Usar los nombres directamente.
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

        List<Badge> badgeList = validateBadges(profile.getBadges(), profile.getUserEmail());

        List<Hobby> hobbyList = validateHobby(profile.getHobbies(), profile.getUserEmail());

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

        profile.setBadges(badgeList.isEmpty() ? null : badgeList);
        profile.setHobbies(hobbyList.isEmpty() ? null : hobbyList);

        this.profilePersistencePort.save(profile);
    }

    @Override
    public Profile getByEmail(String email) {
        return this.profilePersistencePort.getByEmail(email).orElseGet(Profile::new);
    }

    @Override
    public Profile updateEmail(String email, Profile profile) {
        Profile profileStored = profilePersistencePort.getByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException(PROFILE_NOT_FOUND));

        List<Badge> badgeList = validateBadges(profile.getBadges(), email);

        List<Hobby> hobbyList = validateHobby(profile.getHobbies(), email);

        profile.setBadges(badgeList.isEmpty() ? null : badgeList);
        profile.setHobbies(hobbyList.isEmpty() ? null : hobbyList);

        Profile profileToUpdate = updateProfileMapping(profileStored, profile);

        return profilePersistencePort.save(profileToUpdate)
                .orElseThrow(() -> new ProfileNotUpdateException(PROFILE_NOT_UPDATE));
    }

    private List<Badge> validateBadges(List<Badge> badgesList, String userEmail){
        if(isNull(badgesList)) {
            return List.of();
        }

        List<Badge> badgeListResult =  Optional.of(badgesList)
                .map(badgesPersistencePort::findAllBadges)
                .filter(list ->{
                    if(list.isEmpty()) throw new BadgesNotFoundException(BADGES_NOT_FOUND);
                    return true;
                })
                .orElse(List.of());

        if(!badgeListResult.isEmpty() && badgeListResult.size() != badgesList.size()) {
            List<Long> badgeListId = badgeListResult.stream().map(Badge::getId).toList();
            List<Long> badgesNotFound = badgesList.stream()
                    .map(Badge::getId)
                    .filter(badge -> !badgeListId.contains(badge)).toList();
            LOGGER.error(MISSING_INFO + SOME_BADGES_NOT_FOUND, userEmail, badgesNotFound);
        }

        return badgeListResult;
    }

    private List<Hobby> validateHobby(List<Hobby> hobbiesList, String userEmail){
        if(isNull(hobbiesList)){
            return List.of();
        }

        List<Hobby> hobbyListResult = Optional.of(hobbiesList)
                .map(hobbyPersistencePort::findAllById)
                .filter(list -> {
                    if(list.isEmpty()) throw new HobbiesNotFoundException(HOBBIES_NOT_FOUND);
                    return true;
                })
                .orElse(List.of());

        if(!hobbyListResult.isEmpty() && hobbyListResult.size() != hobbiesList.size()){
            List<Long> hobbiesListId = hobbyListResult.stream().map(Hobby::getId).toList();
            List<Long> hobbiesNotFound = hobbiesList.stream()
                    .map(Hobby::getId)
                    .filter(hobby -> !hobbiesListId.contains(hobby)).toList();
            LOGGER.error(MISSING_INFO + SOME_HOBBIES_NOT_FOUND, userEmail, hobbiesNotFound);
        }

        return hobbyListResult;
    }
}
