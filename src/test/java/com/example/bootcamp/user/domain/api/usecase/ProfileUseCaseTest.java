package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.*;
import com.example.bootcamp.user.domain.spi.*;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.bootcamp.user.domain.util.DeveloperRolEnum.BACK;
import static com.example.bootcamp.user.domain.util.SocialMediaEnum.FACEBOOK;
import static com.example.bootcamp.user.domain.util.SocialMediaEnum.INSTAGRAM;
import static com.example.bootcamp.user.domain.util.StaffRolEnum.ADMIN;
import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.TestConstants.*;
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
    @Mock
    private IHobbyPersistencePort hobbyPersistencePort;
    @Mock
    private IDeveloperRolPersistencePort developerRolPersistencePort;
    @Mock
    private IStaffRolPersistencePort staffRolPersistencePort;
    @InjectMocks
    private ProfileUseCase profileUseCase;

    private Profile profileValid;
    private Badge badgeValid;
    private Hobby hobbyValid;

    private Executable executable;

    @BeforeEach
    void setUp() {
        Map<String, String> socialMediaValid;

        badgeValid = new Badge();
        badgeValid.setId(FIRST_ID);

        hobbyValid = new Hobby();
        hobbyValid.setId(FIRST_ID);

        socialMediaValid = new HashMap<>();
        socialMediaValid.put(INSTAGRAM.name(), "https://instagram.com");
        socialMediaValid.put(FACEBOOK.name(), "https://facebook.com");

        profileValid = new Profile();
        profileValid.setUserEmail(VALID_EMAIL);
        profileValid.setBadges(List.of(badgeValid, badgeValid));
        profileValid.setHobbies(List.of(hobbyValid, hobbyValid));
        profileValid.setDeveloperRol(BACK.name());
        profileValid.setStaffRol(ADMIN.name());
        profileValid.setDescription("Test Description");
        profileValid.setSocialMedia(socialMediaValid);

        executable = () -> profileUseCase.save(profileValid);
    }

    @Test
    void save() {
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));
        doNothing().when(profilePersistencePort).save(any(Profile.class));

        profileUseCase.save(profileValid);

        verify(profilePersistencePort, times(ONE_TIME)).save(any(Profile.class));
    }

    @Test
    void save_UserNotFound() {
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.empty());
        when(staffPersistencePort.findByEmail(anyString())).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, executable);

        assertEquals(USER_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_BadgesNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of());

        BadgesNotFoundException ex = assertThrows(BadgesNotFoundException.class, executable);

        assertEquals(BADGES_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_SomeBadgesNotFound(CapturedOutput capturedOutput) {
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));

        String expectedLogMessage = MessageFormatter.arrayFormat(
                MISSING_INFO + SOME_BADGES_NOT_FOUND,
                new Object[]{VALID_EMAIL, List.of()}
        ).getMessage();

        profileUseCase.save(profileValid);

        assertThat(capturedOutput.getOut()).contains(expectedLogMessage);
    }

    @Test
    void save_HobbiesNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of());

        HobbiesNotFoundException ex = assertThrows(HobbiesNotFoundException.class, executable);

        assertEquals(HOBBIES_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_SomeHobbiesNotFound(CapturedOutput capturedOutput){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));

        String expectedLogMessage = MessageFormatter.arrayFormat(
                MISSING_INFO + SOME_HOBBIES_NOT_FOUND,
                new Object[]{VALID_EMAIL, List.of()}
        ).getMessage();

        profileUseCase.save(profileValid);

        assertThat(capturedOutput.getOut()).contains(expectedLogMessage);
    }

    @Test
    void save_DeveloperRolNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.empty());

        DeveloperRolNotFoundException ex = assertThrows(DeveloperRolNotFoundException.class, executable);

        assertEquals(DEVELOPER_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_StaffRolNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.empty());

        StaffRolNotFoundException ex = assertThrows(StaffRolNotFoundException.class, executable);

        assertEquals(STAFF_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_DescriptionTooLong(){
        profileValid.setDescription("A".repeat(DESCRIPTION_TOO_LONG));

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));

        DescriptionTooLongException ex = assertThrows(DescriptionTooLongException.class, executable);

        assertEquals(DESCRIPTION_TOO_LONG_MESSAGE, ex.getMessage());
    }

    @Test
    void save_DescriptionNull(){
        profileValid.setDescription(null);

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));

        DescriptionTooLongException ex = assertThrows(DescriptionTooLongException.class, executable);

        assertEquals(DESCRIPTION_TOO_LONG_MESSAGE, ex.getMessage());
    }

    @Test
    void save_InvalidUrl(){
        profileValid.getSocialMedia().put(INSTAGRAM.name(), "insta.com");

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));

        InvalidUrlException ex = assertThrows(InvalidUrlException.class, executable);

        assertEquals(URL_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_BadgesListIsNull(){
        profileValid.setBadges(null);

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));
        doNothing().when(profilePersistencePort).save(any(Profile.class));

        profileUseCase.save(profileValid);

        verify(profilePersistencePort, times(ONE_TIME)).save(any(Profile.class));
    }

    @Test
    void save_HobbiesListIsNull(){
        profileValid.setHobbies(null);

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new DeveloperRol()));
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(new StaffRol()));
        doNothing().when(profilePersistencePort).save(any(Profile.class));

        profileUseCase.save(profileValid);

        verify(profilePersistencePort, times(ONE_TIME)).save(any(Profile.class));
    }

    @Test
    void save_DeveloperRolIsNull(){
        profileValid.setDeveloperRol(null);

        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.of(new Student()));
        when(badgesPersistencePort.findAllBadges(anyList())).thenReturn(List.of(badgeValid, badgeValid));
        when(hobbyPersistencePort.findAllById(anyList())).thenReturn(List.of(hobbyValid, hobbyValid));

        DeveloperRolNotFoundException ex = assertThrows(DeveloperRolNotFoundException.class, executable);

        assertEquals(DEVELOPER_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_UserIdIsNUll() {
        profileValid.setUserEmail(null);

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, executable);

        assertEquals(USER_NOT_FOUND, ex.getMessage());
    }
}