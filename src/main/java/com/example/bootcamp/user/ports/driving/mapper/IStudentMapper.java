package com.example.bootcamp.user.ports.driving.mapper;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStudentMapper {

    @Mapping(target = "educationLevel.name", source = "educationLevel")
    @Mapping(target = "developerRol.name", source = "developerRol")
    @Mapping(target = "courseDiscoverySource.name", source = "courseDiscoverySource")
    Student toStudent(StudentRegisterDto studentRegisterDto);

    @Mapping(target = "institution.id", source = "institutionId")
    StudentInstitution toStudentInstitution(StudentRegisterDto studentRegisterDto);
}
