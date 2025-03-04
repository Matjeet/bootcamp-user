package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
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
    @InjectMocks
    private StudentUseCase studentUseCase;

    Student studentValid;
    StudentInstitution studentInstitution;
    Executable executable;
    Executable executableWithValidData;

    @BeforeEach
    void setUp() {
        studentValid = new Student();
        studentValid.setEmail("mateo.velasquez@pragma.com.co");
        studentValid.setCellphone("+573226748955");
        studentValid.setIdentification("2000757896");
        studentValid.setBirthDate(LocalDate.of(2002,11,29));

        studentInstitution = new StudentInstitution();

        executable = () -> studentUseCase.save(new Student(), new StudentInstitution());
        executableWithValidData = () -> studentUseCase.save(studentValid, new StudentInstitution());
    }

    @Test
    void saveSuccess(){
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(null);
        doNothing().when(studentPersistencePort).save(studentValid, studentInstitution);

        studentUseCase.save(studentValid, studentInstitution);

        verify(studentPersistencePort, times(ONE_TIME)).save(studentValid, studentInstitution);
        verify(studentPersistencePort, times(ONE_TIME)).findByEmailOrIdentification(any(), any());
    }

    @Test
    void saveEmailExistInDataBase() {
        when(studentPersistencePort.findByEmailOrIdentification(any(), any())).thenReturn(new Student());

        StudentExistsException ex = assertThrows(StudentExistsException.class, executableWithValidData);

        assertEquals(STUDENT_EXIST, ex.getMessage());
    }

    @Test
    void saveInvalidEmail() {
        InvalidEmailException ex = assertThrows(InvalidEmailException.class, executable);

        assertEquals(EMAIL_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void saveInvalidCellphone(){
        studentValid.setCellphone("+1nv4l1d_c311ph0n3");

        InvalidCellphoneException ex = assertThrows(InvalidCellphoneException.class, executableWithValidData);

        assertEquals(CELLPHONE_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void saveInvalidIdentification(){
        studentValid.setIdentification("invalid_identification");

        InvalidIdentificationException ex = assertThrows(InvalidIdentificationException.class, executableWithValidData);

        assertEquals(IDENTIFICATION_INVALID_MESSAGE, ex.getMessage());
    }

    @Test
    void saveStudentIsNotAnAdult(){
        LocalDate mockBirthDate = LocalDate.now();
        studentValid.setBirthDate(mockBirthDate);

        InvalidAgeException ex = assertThrows(InvalidAgeException.class, executableWithValidData);

        assertEquals(STUDENT_IS_NOT_AN_ADULT, ex.getMessage());
    }
}