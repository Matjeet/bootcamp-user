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

import java.time.LocalDate;
import java.util.Optional;

import static com.example.bootcamp.user.domain.util.DeveloperRolEnum.BACK;
import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.TestConstants.VALID_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentUseCaseTest {

    @Mock
    private IStudentPersistencePort studentPersistencePort;
    @Mock
    private IInstitutionPersistencePort institutionPersistencePort;
    @Mock
    private IEducationLevelPersistencePort educationLevelPersistencePort;
    @Mock
    private IDeveloperRolPersistencePort developerRolPersistencePort;
    @Mock
    private ISourcePersistencePort sourcePersistencePort;
    @Mock
    private ICityPersistencePort cityPersistencePort;
    @Mock
    private IStudentInstitutionPersistencePort studentInstitutionPersistencePort;
    @InjectMocks
    private StudentUseCase studentUseCase;

    private Student studentValid;
    private StudentInstitution studentInstitutionValid;
    private Institution institutionValid;
    private EducationLevel educationLevelValid;
    private DeveloperRol developerRolValid;
    private Source sourceValid;
    private Location locationValid;

    private Executable executable;
    private Executable executableWithValidData;

    @BeforeEach
    void setUp() {
        educationLevelValid = new EducationLevel();
        educationLevelValid.setName("TECHNICAL");

        developerRolValid = new DeveloperRol();
        developerRolValid.setName(BACK.name());

        sourceValid = new Source();
        sourceValid.setName("LINKEDIN");

        locationValid = new Location();
        locationValid.setCityId(1L);

        studentValid = new Student();
        studentValid.setEmail(VALID_EMAIL);
        studentValid.setCellphone("+573226748955");
        studentValid.setIdentification("2000757896");
        studentValid.setBirthDate(LocalDate.of(2002,11,29));
        studentValid.setEducationLevel(educationLevelValid);
        studentValid.setDeveloperRol(developerRolValid);
        studentValid.setCourseDiscoverySource(sourceValid);
        studentValid.setLocation(locationValid);

        institutionValid = new Institution();
        institutionValid.setId(1L);

        studentInstitutionValid = new StudentInstitution();
        studentInstitutionValid.setInstitution(institutionValid);

        executable = () -> studentUseCase.save(new Student(), new StudentInstitution());
        executableWithValidData = () -> studentUseCase.save(studentValid, studentInstitutionValid);
    }

    @Test
    void saveSuccess(){
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(Optional.empty());
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.of(institutionValid));
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(Optional.of(educationLevelValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(developerRolValid));
        when(sourcePersistencePort.findByName(anyString())).thenReturn(Optional.of(sourceValid));
        when(cityPersistencePort.findById(anyLong())).thenReturn(Optional.of(new City()));
        doNothing().when(studentInstitutionPersistencePort).save(studentInstitutionValid);

        studentUseCase.save(studentValid, studentInstitutionValid);

        verify(studentInstitutionPersistencePort, times(ONE_TIME)).save(studentInstitutionValid);
        verify(studentPersistencePort, times(ONE_TIME)).findByEmailOrIdentification(any(), any());
    }

    @Test
    void save_EmailExistInDataBase() {
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(Optional.of(studentValid));

        StudentExistsException ex = assertThrows(StudentExistsException.class, executableWithValidData);

        assertEquals(STUDENT_EXIST, ex.getMessage());
    }

    @Test
    void save_InvalidEmail() {
        InvalidEmailException ex = assertThrows(InvalidEmailException.class, executable);

        assertEquals(EMAIL_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_InvalidCellphone(){
        studentValid.setCellphone("+1nv4l1d_c311ph0n3");

        InvalidCellphoneException ex = assertThrows(InvalidCellphoneException.class, executableWithValidData);

        assertEquals(CELLPHONE_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_InvalidIdentification(){
        studentValid.setIdentification("invalid_identification");

        InvalidIdentificationException ex = assertThrows(InvalidIdentificationException.class, executableWithValidData);

        assertEquals(IDENTIFICATION_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void save_StudentIsNotAnAdult(){
        LocalDate mockBirthDate = LocalDate.now();
        studentValid.setBirthDate(mockBirthDate);

        InvalidAgeException ex = assertThrows(InvalidAgeException.class, executableWithValidData);

        assertEquals(STUDENT_IS_NOT_AN_ADULT, ex.getMessage());
    }

    @Test
    void save_InstitutionNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.empty());
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        InstitutionNotFoundException ex = assertThrows(InstitutionNotFoundException.class, executableWithValidData);

        assertEquals(INSTITUTION_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_EducationLevelNotFound(){
        when(studentPersistencePort.findByEmailOrIdentification(anyString(), anyString())).thenReturn(Optional.empty());
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.of(institutionValid));
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(Optional.empty());

        EducationLevelNotFoundException ex = assertThrows(EducationLevelNotFoundException.class, executableWithValidData);

        assertEquals(EDUCATION_LEVEL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_DeveloperRolNotFound(){
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.of(institutionValid));
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(Optional.of(educationLevelValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.empty());

        DeveloperRolNotFoundException ex = assertThrows(DeveloperRolNotFoundException.class, executableWithValidData);

        assertEquals(DEVELOPER_ROL_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_SourceNotFound(){
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.of(institutionValid));
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(Optional.of(educationLevelValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(developerRolValid));
        when(sourcePersistencePort.findByName(anyString())).thenReturn(Optional.empty());

        SourceNotFoundException ex = assertThrows(SourceNotFoundException.class, executableWithValidData);

        assertEquals(SOURCE_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_CityNotFound(){
        when(institutionPersistencePort.findById(anyLong())).thenReturn(Optional.of(institutionValid));
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(Optional.of(educationLevelValid));
        when(developerRolPersistencePort.findByName(anyString())).thenReturn(Optional.of(developerRolValid));
        when(sourcePersistencePort.findByName(anyString())).thenReturn(Optional.of(sourceValid));
        when(cityPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        CityNotFoundException ex = assertThrows(CityNotFoundException.class, executableWithValidData);

        assertEquals(CITY_NOT_FOUND, ex.getMessage());
    }
}