package com.example.bootcamp.user.ports.driving.dto.request;

import com.example.bootcamp.user.domain.util.DeveloperRolEnum;
import com.example.bootcamp.user.domain.util.StaffRolEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.StudentMessage.LASTNAME_IS_NULL_MESSAGE;

@Data
public class StaffRegisterDto {

    @NotBlank(message = NAME_IS_BLANK_MESSAGE)
    @NotNull(message = NAME_IS_NULL_MESSAGE)
    private String name;

    @NotBlank(message = LASTNAME_IS_BLANK_MESSAGE)
    @NotNull(message = LASTNAME_IS_NULL_MESSAGE)
    private String lastName;

    @Email(message = EMAIL_INVALID_MESSAGE)
    @NotNull(message = EMAIL_IS_NULL_MESSAGE)
    private String email;

    @NotNull(message = DEVELOPER_ROL_IS_NULL_MESSAGE)
    private DeveloperRolEnum developerRol;

    @NotNull(message = STAFF_ROLL_IS_NULL_MESSAGE)
    private StaffRolEnum staffRol;
}
