package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.exception.UserNotFoundException;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.IProfilePersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;

import static com.example.bootcamp.user.domain.util.StudentMessage.USER_NOT_FOUND;

public class ProfileUseCase implements IProfileServicePort {

    private final IProfilePersistencePort profilePersistencePort;
    private final IStudentPersistencePort studentPersistencePort;
    private final IStaffPersistencePort staffPersistencePort;

    public ProfileUseCase(
            IProfilePersistencePort profilePersistencePort,
            IStudentPersistencePort studentPersistencePort,
            IStaffPersistencePort staffPersistencePort
    ){
        this.profilePersistencePort = profilePersistencePort;
        this.studentPersistencePort = studentPersistencePort;
        this.staffPersistencePort = staffPersistencePort;
    }

    @Override
    public void save(Profile profile) {

        if(
            studentPersistencePort.findById(profile.getUserId()).isEmpty()
            && staffPersistencePort.findById(profile.getUserId()).isEmpty()
        ){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        this.profilePersistencePort.save(profile);
    }
}
