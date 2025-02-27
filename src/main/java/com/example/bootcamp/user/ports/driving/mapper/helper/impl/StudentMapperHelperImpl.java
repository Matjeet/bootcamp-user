package com.example.bootcamp.user.ports.driving.mapper.helper.impl;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;
import com.example.bootcamp.user.ports.driving.mapper.IStudentMapper;
import com.example.bootcamp.user.ports.driving.mapper.helper.IStudentMapperHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentMapperHelperImpl implements IStudentMapperHelper {

    private final IStudentMapper studentMapper;


    @Override
    public Student toStudent(StudentRegisterDto studentRegisterDto) {
        return studentMapper.toStudent(studentRegisterDto);
    }

    @Override
    public StudentInstitution toStudentInstitution(StudentRegisterDto studentRegisterDto) {
        return studentMapper.toStudentInstitution(studentRegisterDto);
    }
}
