package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.EducationLevel;
import com.example.bootcamp.user.domain.model.Institution;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.domain.spi.IEducationLevelPersistencePort;
import com.example.bootcamp.user.domain.spi.IInstitutionPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.example.bootcamp.user.domain.util.StudentConstants.ONE_TIME;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
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
    @InjectMocks
    private StudentUseCase studentUseCase;

    private Student studentValid;
    private StudentInstitution studentInstitutionValid;
    private Institution institutionValid;
    private EducationLevel educationLevelValid;

    private Executable executable;
    private Executable executableWithValidData;

    @BeforeEach
    void setUp() {
        educationLevelValid = new EducationLevel();
        educationLevelValid.setName("TECHNICAL");

        studentValid = new Student();
        studentValid.setEmail("mateo.velasquez@pragma.com.co");
        studentValid.setCellphone("+573226748955");
        studentValid.setIdentification("2000757896");
        studentValid.setBirthDate(LocalDate.of(2002,11,29));
        studentValid.setEducationLevel(educationLevelValid);

        institutionValid = new Institution();
        institutionValid.setId(1L);

        studentInstitutionValid = new StudentInstitution();
        studentInstitutionValid.setInstitution(institutionValid);

        executable = () -> studentUseCase.save(new Student(), new StudentInstitution());
        executableWithValidData = () -> studentUseCase.save(studentValid, studentInstitutionValid);
    }

    @Test
    void saveSuccess(){
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(null);
        when(institutionPersistencePort.findById(anyLong())).thenReturn(institutionValid);
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(educationLevelValid);
        doNothing().when(studentPersistencePort).save(studentValid, studentInstitutionValid);

        studentUseCase.save(studentValid, studentInstitutionValid);

        verify(studentPersistencePort, times(ONE_TIME)).save(studentValid, studentInstitutionValid);
        verify(studentPersistencePort, times(ONE_TIME)).findByEmailOrIdentification(any(), any());
    }

    @Test
    void save_EmailExistInDataBase() {
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(new Student());

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
        when(institutionPersistencePort.findById(anyLong())).thenReturn(null);

        InstitutionNotFoundException ex = assertThrows(InstitutionNotFoundException.class, executableWithValidData);

        assertEquals(INSTITUTION_NOT_FOUND, ex.getMessage());
    }

    @Test
    void save_EducationLevelNotFound(){
        when(institutionPersistencePort.findById(anyLong())).thenReturn(institutionValid);
        when(educationLevelPersistencePort.findByName(anyString())).thenReturn(null);

        EducationLevelNotFoundException ex = assertThrows(EducationLevelNotFoundException.class, executableWithValidData);

        assertEquals(EDUCATION_LEVEL_NOT_FOUND, ex.getMessage());
    }
}