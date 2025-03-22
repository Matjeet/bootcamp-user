package com.example.bootcamp.user.ports.driving.dto.request;

import com.example.bootcamp.user.domain.util.CourseDiscoverySource;
import com.example.bootcamp.user.domain.util.DeveloperRolEnum;
import com.example.bootcamp.user.domain.util.EducationLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

import static com.example.bootcamp.user.domain.util.StudentConstants.*;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;

@Data
public class StudentRegisterDto {

    @NotBlank(message = NAME_IS_BLANK_MESSAGE)
    @NotNull(message = NAME_IS_NULL_MESSAGE)
    private String name;

    @NotBlank(message = LASTNAME_IS_BLANK_MESSAGE)
    @NotNull(message = LASTNAME_IS_NULL_MESSAGE)
    private String lastname;

    @Email(message = EMAIL_INVALID_MESSAGE)
    @NotNull(message = EMAIL_IS_NULL_MESSAGE)
    private String email;

    @NotNull(message = CELLPHONE_IS_NULL_MESSAGE)
    @Pattern(regexp = CELLPHONE_REGEX, message = CELLPHONE_INVALID_MESSAGE)
    private String cellphone;

    @NotNull(message = IDENTIFICATION_IS_NULL_MESSAGE)
    @Min(value = IDENTIFICATION_MIN_LENGTH, message = IDENTIFICATION_TOO_SHORT_MESSAGE)
    @Pattern(regexp = IDENTIFICATION_REGEX, message = IDENTIFICATION_INVALID_MESSAGE)
    private String identification;

    @NotNull(message = IDENTIFICATION_TYPE_IS_NULL_MESSAGE)
    @Size(min = IDENTIFICATION_TYPE_MIN_LENGTH, max = IDENTIFICATION_TYPE_MAX_LENGTH, message = IDENTIFICATION_TYPE_INVALID_LENGTH_MESSAGE)
    private String identificationType;

    @Past(message = BIRTHDATE_IS_NOT_PAST_DATE_MESSAGE)
    @NotNull(message = BIRTHDATE_IS_NULL_MESSAGE)
    private LocalDate birthDate;

    @NotNull(message = EDUCATION_LEVEL_IS_NULL_MESSAGE)
    private EducationLevel educationLevel;

    @NotNull(message = INSTITUTION_ID_IS_NULL_MESSAGE)
    @Positive(message = INSTITUTION_ID_IS_NOT_A_NUMBER_MESSAGE)
    private Long institutionId;

    @NotBlank(message = INSTITUTION_DETAIL_IS_BLANK_MESSAGE)
    private String institutionDetail;

    @NotNull(message = DEGREE_NAME_IS_NULL_MESSAGE)
    @NotBlank(message = DEGREE_NAME_IS_BLANK_MESSAGE)
    private String degreeName;

    @NotNull(message = DEVELOPER_ROL_IS_NULL_MESSAGE)
    private DeveloperRolEnum developerRol;

    @NotNull(message = COURSE_DISCOVERY_SOURCE_IS_NULL_MESSAGE)
    private CourseDiscoverySource courseDiscoverySource;

    @NotNull(message = LOCATION_IS_NULL_MESSAGE)
    @Valid
    private LocationDto location;
}
