package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.exception.BadgesNotFoundException;
import com.example.bootcamp.user.domain.exception.UserNotFoundException;
import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.IBadgesPersistencePort;
import com.example.bootcamp.user.domain.spi.IProfilePersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;

public class ProfileUseCase implements IProfileServicePort {

    private final IProfilePersistencePort profilePersistencePort;
    private final IStudentPersistencePort studentPersistencePort;
    private final IStaffPersistencePort staffPersistencePort;
    private final IBadgesPersistencePort badgesPersistencePort;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileUseCase.class);

    public ProfileUseCase(
            IProfilePersistencePort profilePersistencePort,
            IStudentPersistencePort studentPersistencePort,
            IStaffPersistencePort staffPersistencePort,
            IBadgesPersistencePort badgesPersistencePort
    ){
        this.profilePersistencePort = profilePersistencePort;
        this.studentPersistencePort = studentPersistencePort;
        this.staffPersistencePort = staffPersistencePort;
        this.badgesPersistencePort = badgesPersistencePort;
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

        this.profilePersistencePort.save(profile);
    }
}
