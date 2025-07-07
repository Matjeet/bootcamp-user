package com.example.bootcamp.user.ports.driving.controller;

import com.example.bootcamp.user.domain.api.IProfileServicePort;
import com.example.bootcamp.user.domain.api.IStaffServicePort;
import com.example.bootcamp.user.domain.api.IStudentServicePort;
import com.example.bootcamp.user.domain.model.Profile;
import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;
import com.example.bootcamp.user.ports.driving.dto.request.CreateProfileDto;
import com.example.bootcamp.user.ports.driving.dto.request.StaffRegisterDto;
import com.example.bootcamp.user.ports.driving.dto.request.StudentRegisterDto;
import com.example.bootcamp.user.ports.driving.dto.request.UpdateProfileDto;
import com.example.bootcamp.user.ports.driving.dto.response.ProfileResponseDto;
import com.example.bootcamp.user.ports.driving.mapper.IProfileMapper;
import com.example.bootcamp.user.ports.driving.mapper.IStaffMapper;
import com.example.bootcamp.user.ports.driving.mapper.IStudentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.ports.driving.util.DocumentationConstants.*;

@Tag(name = USER_TAG, description = USER_DESCRIPTION)
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IStudentMapper studentMapper;
    private final IStudentServicePort studentServicePort;
    private final IStaffMapper staffMapper;
    private final IStaffServicePort staffServicePort;
    private final IProfileServicePort profileServicePort;
    private final IProfileMapper profileMapper;

    private final Map<String, String> simpleResponse = new HashMap<>();

    @Operation(
            summary = CREATE_STUDENT_SUMMARY,
            description = CREATE_STUDENT_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_BAD_REQUEST_CODE, description = BAD_REQUEST_400_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)

    @PostMapping("/student")
    public ResponseEntity<Map<String, String>> saveStudent(@Valid @RequestBody StudentRegisterDto studentRegisterDto){
        StudentInstitution studentInstitution = studentMapper.toStudentInstitution(studentRegisterDto);
        Student student = studentMapper.toStudent(studentRegisterDto);
        studentServicePort.save(student, studentInstitution);

        simpleResponse.put(MESSAGE_KEY, CREATED_STUDENT_MESSAGE);

        return ResponseEntity.status(HttpStatus.CREATED).body(simpleResponse);
    }

    @Operation(
            summary = CREATE_STAFF_SUMMARY,
            description = CREATE_STAFF_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_BAD_REQUEST_CODE, description = BAD_REQUEST_400_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)

    @PostMapping("/staff")
    public ResponseEntity<Map<String, String>> saveStaff(@Valid @RequestBody StaffRegisterDto staffRegisterDto){
        staffServicePort.save(staffMapper.toModel(staffRegisterDto));

        simpleResponse.put(MESSAGE_KEY, CREATED_STAFF_MESSAGE);

        return ResponseEntity.status(HttpStatus.CREATED).body(simpleResponse);
    }

    @Operation(
            summary = CREATE_PROFILE_SUMMARY,
            description = CREATE_PROFILE_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_BAD_REQUEST_CODE, description = BAD_REQUEST_400_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)

    @PostMapping(value = "/profile")
    public ResponseEntity<Map<String, String>> saveProfile(@Valid @RequestBody CreateProfileDto createProfileDto){
        Profile profile = profileMapper.fromCreateToModel(createProfileDto);
        profileServicePort.save(profile);

        simpleResponse.put(MESSAGE_KEY, CREATED_PROFILE_MESSAGE);

        return ResponseEntity.status(HttpStatus.CREATED).body(simpleResponse);
    }

    @Operation(
            summary = GET_PROFILE_SUMMARY,
            description = GET_PROFILE_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)

    @GetMapping(value = "/profile")
    public ResponseEntity<ProfileResponseDto> getProfile(@RequestParam(value = "email") String email){
        return ResponseEntity.status(HttpStatus.OK)
                .body(profileMapper.toProfileResponse(profileServicePort.getByEmail(email)));
    }

    @Operation(
            summary = UPDATE_PROFILE_SUMMARY,
            description = UPDATE_PROFILE_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_BAD_REQUEST_CODE, description = BAD_REQUEST_400_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_UPDATE_PROFILE_DESCRIPTION)

    @PutMapping(value = "/profile")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @RequestParam(value = "email") String email,
            @RequestBody UpdateProfileDto updateProfileDto
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileMapper.toProfileResponse(
                        profileServicePort.update(email, profileMapper.fromUpdateToModel(updateProfileDto))
                ));
    }

    @Operation(
            summary = DELETE_PROFILE_SUMMARY,
            description = DELETE_PROFILE_DESCRIPTION
    )
    @ApiResponse(responseCode = HTTP_CREATED_CODE, description = CREATED_201_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_BAD_REQUEST_CODE, description = BAD_REQUEST_400_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_DESCRIPTION)
    @ApiResponse(responseCode = HTTP_INTERNAL_ERROR_CODE, description = INTERNAL_ERROR_500_UPDATE_PROFILE_DESCRIPTION)

    @DeleteMapping(value = "/profile")
    public ResponseEntity<Void> deleteProfile(@RequestParam(value = "email") String email){
        profileServicePort.delete(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
