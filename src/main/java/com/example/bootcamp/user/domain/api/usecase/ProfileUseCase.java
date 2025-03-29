package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.exception.BadgesNotFoundException;
import com.example.bootcamp.user.domain.exception.DeveloperRolNotFoundException;
import com.example.bootcamp.user.domain.exception.HobbiesNotFoundException;
import com.example.bootcamp.user.domain.exception.UserNotFoundException;
import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Hobby;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;

public class ProfileUseCase implements IProfileServicePort {

    private final IProfilePersistencePort profilePersistencePort;
    private final IStudentPersistencePort studentPersistencePort;
    private final IStaffPersistencePort staffPersistencePort;
    private final IBadgesPersistencePort badgesPersistencePort;
    private final IHobbyPersistencePort hobbyPersistencePort;
    private final IDeveloperRolPersistencePort developerRolPersistencePort;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileUseCase.class);

    public ProfileUseCase(
            IProfilePersistencePort profilePersistencePort,
            IStudentPersistencePort studentPersistencePort,
            IStaffPersistencePort staffPersistencePort,
            IBadgesPersistencePort badgesPersistencePort,
            IHobbyPersistencePort hobbyPersistencePort,
            IDeveloperRolPersistencePort developerRolPersistencePort
    ){
        this.profilePersistencePort = profilePersistencePort;
        this.studentPersistencePort = studentPersistencePort;
        this.staffPersistencePort = staffPersistencePort;
        this.badgesPersistencePort = badgesPersistencePort;
        this.hobbyPersistencePort = hobbyPersistencePort;
        this.developerRolPersistencePort = developerRolPersistencePort;
    }

    @Override
    public void save(Profile profile) {

        if(
            studentPersistencePort.findById(profile.getUserId()).isEmpty()
            && staffPersistencePort.findById(profile.getUserId()).isEmpty()
        ){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        List<Badge> badgeList = badgesPersistencePort.findAllBadges(profile.getBadges());
        if(badgeList.isEmpty()) throw new BadgesNotFoundException(BADGES_NOT_FOUND);
        if(badgeList.size() != profile.getBadges().size()) {
            List<Badge> badgesNotFound = profile.getBadges().stream().filter(badge -> !badgeList.contains(badge)).toList();
            LOGGER.error(MISSING_INFO + SOME_BADGES_NOT_FOUND, profile.getUserId(), badgesNotFound);
        }

        List<Hobby> hobbyList = hobbyPersistencePort.findAllById(profile.getHobbies());
        if(hobbyList.isEmpty()) throw new HobbiesNotFoundException(HOBBIES_NOT_FOUND);
        if(hobbyList.size() != profile.getHobbies().size()){
            List<Hobby> hobbiesNotFound = profile.getHobbies().stream().filter(hobby -> !hobbyList.contains(hobby)).toList();
            LOGGER.error(MISSING_INFO + SOME_HOBBIES_NOT_FOUND, profile.getUserId(), hobbiesNotFound);
        }

        if(developerRolPersistencePort.findByName(profile.getDeveloperRol()).isEmpty()) throw new DeveloperRolNotFoundException(DEVELOPER_ROL_NOT_FOUND);

        profile.setBadges(badgeList);
        profile.setHobbies(hobbyList);

        this.profilePersistencePort.save(profile);
    }
}
