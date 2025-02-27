package com.example.bootcamp.user.ports.driving.mapper.helper;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;

public interface IStudentMapperHelper {
    Student toStudent(StudentRegisterDto studentRegisterDto);
    StudentInstitution toStudentInstitution(StudentRegisterDto studentRegisterDto);
}
