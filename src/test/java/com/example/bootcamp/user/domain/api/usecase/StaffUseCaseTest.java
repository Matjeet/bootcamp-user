package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.DeveloperRol;
import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.domain.model.StaffRol;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.spi.IDeveloperRolPersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffRolPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.bootcamp.user.domain.util.DeveloperRolEnum.BACK;
import static com.example.bootcamp.user.domain.util.StaffRolEnum.ADMIN;
import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.TestConstants.INVALID_EMAIL;
import static com.example.bootcamp.user.domain.util.TestConstants.VALID_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StaffUseCaseTest {

    @Mock
    private IStaffPersistencePort staffPersistencePort;
    @Mock
    private IDeveloperRolPersistencePort developerRolPersistencePort;
    @Mock
    private IStaffRolPersistencePort staffRolPersistencePort;
    @Mock
    private IStudentPersistencePort studentPersistencePort;
    @InjectMocks
    private StaffUseCase staffUseCase;

    private Staff staffValid;
    private DeveloperRol developerRolValid;
    private StaffRol staffRolValid;

    private Executable executable;

    @BeforeEach
    void setUp() {
        staffRolValid = new StaffRol();
        staffRolValid.setName(ADMIN.name());

        developerRolValid = new DeveloperRol();
        developerRolValid.setName(BACK.name());

        staffValid = new Staff();
        staffValid.setEmail(VALID_EMAIL);
        staffValid.setDeveloperRol(developerRolValid);
        staffValid.setStaffRol(staffRolValid);

        executable = () -> staffUseCase.save(staffValid);
    }

    @Test
    void save() {
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(developerRolValid);
        when(staffRolPersistencePort.findByName(anyString())).thenReturn(staffRolValid);
        doNothing().when(staffPersistencePort).save(staffValid);

        staffUseCase.save(staffValid);

        verify(staffPersistencePort, times(ONE_TIME)).save(staffValid);
    }

    @Test
    void save_InvalidEmail() {
        staffValid.setEmail(INVALID_EMAIL);

        InvalidEmailException ex = assertThrows(InvalidEmailException.class, executable);

        assertEquals(EMAIL_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_EmailIsNull(){
        staffValid.setEmail(null);

        InvalidEmailException ex = assertThrows(InvalidEmailException.class, executable);

        assertEquals(EMAIL_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_StaffMemberExist(){
        when(staffPersistencePort.findByEmail(anyString())).thenReturn(staffValid);

        StaffMemberExistException ex = assertThrows(StaffMemberExistException.class, executable);

        assertEquals(STAFF_MEMBER_EXIST, ex.getMessage());
    }

    @Test
    void save_DeveloperRolNotFound(){
        DeveloperRolNotFoundException ex = assertThrows(DeveloperRolNotFoundException.class, executable);

        assertEquals(DEVELOPER_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_StaffRolNotFound(){
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(developerRolValid);

        StaffRolNotFoundException ex = assertThrows(StaffRolNotFoundException.class, executable);

        assertEquals(STAFF_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_EmailIsRegisteredAsAStudent(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(new Student());

        EmailRegisteredAsAStudentException ex = assertThrows(EmailRegisteredAsAStudentException.class, executable);

        assertEquals(EMAIL_REGISTERED_AS_A_STUDENT, ex.getMessage());
    }
}