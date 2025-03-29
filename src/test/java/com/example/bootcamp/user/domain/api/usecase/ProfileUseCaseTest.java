package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.BadgesNotFoundException;
import com.example.bootcamp.user.domain.exception.UserNotFoundException;
import com.example.bootcamp.user.domain.model.Badge;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.spi.IBadgesPersistencePort;
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
import org.slf4j.helpers.MessageFormatter;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;
import java.util.Optional;

import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.TestConstants.FIRST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
class ProfileUseCaseTest {

    @Mock
    private IProfilePersistencePort profilePersistencePort;
    @Mock
    private IStudentPersistencePort studentPersistencePort;
    @Mock
    private IStaffPersistencePort staffPersistencePort;
    @Mock
    private IBadgesPersistencePort badgesPersistencePort;
    @InjectMocks
    private ProfileUseCase profileUseCase;

    private Profile profileValid;
    private Badge badgeValid;

    private Executable executable;

    @BeforeEach
    void setUp() {
        badgeValid = new Badge();
        badgeValid.setId(FIRST_ID);

        profileValid = new Profile();
        profileValid.setUserId(FIRST_ID);
        profileValid.setBadges(List.of(badgeValid, badgeValid));

        executable = () -> profileUseCase.save(profileValid);
    }

    @Test
    void save() {
        when(studentPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        doNothing().when(profilePersistencePort).save(any(Profile.class));

        profileUseCase.save(profileValid);

        verify(profilePersistencePort, times(ONE_TIME)).save(any(Profile.class));
    }

    @Test
    void save_UserNotFound() {
        when(studentPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        when(staffPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, executable);

        assertEquals(USER_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_BadgesNotFound(){
        when(studentPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of());

        BadgesNotFoundException ex = assertThrows(BadgesNotFoundException.class, executable);

        assertEquals(BADGES_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_BadgesWithDifferentSize(CapturedOutput capturedOutput) {
        when(studentPersistencePort.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid));

        String expectedLogMessage = MessageFormatter.arrayFormat(
                MISSING_INFO + SOME_BADGES_NOT_FOUND,
                new Object[]{FIRST_ID, List.of()}
        ).getMessage();

        profileUseCase.save(profileValid);

        assertThat(capturedOutput.getOut()).contains(expectedLogMessage);
    }
}