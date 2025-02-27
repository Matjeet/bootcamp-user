package com.example.bootcamp.user.ports.driving.dto.request;

import com.example.bootcamp.user.domain.util.CourseDiscoverySource;
import com.example.bootcamp.user.domain.util.DeveloperRolEnum;
import com.example.bootcamp.user.domain.util.EducationLevel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRegisterDto {

    private String name;
    private String lastname;
    private String email;
    private String cellphone;
    private String identification;
    private String identificationType;
    private LocalDate birthDate;
    private EducationLevel educationLevel;
    private Long institution;
    private String institutionDetail;
    private String degreeName;
    private DeveloperRolEnum developerRol;
    private CourseDiscoverySource courseDiscoverySource;
    private LocationDto location;
}
