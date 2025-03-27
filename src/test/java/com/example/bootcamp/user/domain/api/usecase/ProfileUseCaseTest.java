package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.UserNotFoundException;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.spi.IProfilePersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.USER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileUseCaseTest {

    @Mock
    private IProfilePersistencePort profilePersistencePort;
    @Mock
    private IStudentPersistencePort studentPersistencePort;
    @Mock
    private IStaffPersistencePort staffPersistencePort;
    @InjectMocks
    private ProfileUseCase profileUseCase;

    private Profile profileValid;

    private Executable executable;

    @BeforeEach
    void setUp() {
        profileValid = new Profile();

        executable = () -> profileUseCase.save(profileValid);
    }

    @Test
    void save() {

        doNothing().when(profilePersistencePort).save(any(Profile.class));

        profileUseCase.save(profileValid);

        verify(profilePersistencePort, times(ONE_TIME)).save(any(Profile.class));
    }

    @Test
    void save_UserNotFound() {

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, executable);

        assertEquals(USER_NOT_FOUND, ex.getMessage());
    }
}