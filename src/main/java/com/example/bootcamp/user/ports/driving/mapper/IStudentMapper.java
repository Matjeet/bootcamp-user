package com.example.bootcamp.user.ports.driving.mapper;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStudentMapper {

    //@Mapping(target = "educationLevel", source = "educationLevel.name")
    @ValueMapping(target = "educationLevel", source = "educationLevel.name")
    Student toStudent(StudentRegisterDto studentRegisterDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "student", ignore = true)
    StudentInstitution toStudentInstitution(StudentRegisterDto studentRegisterDto);
}
