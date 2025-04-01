package com.example.bootcamp.user.ports.driving.dto.request;

import com.example.bootcamp.user.domain.util.DeveloperRolEnum;
import com.example.bootcamp.user.domain.util.SocialMediaEnum;
import com.example.bootcamp.user.domain.util.StaffRolEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentConstants.DESCRIPTION_MAX_LENGTH;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;

@Data
public class CreateProfileDto {

    @NotNull(message = USER_ID_IS_NULL_MESSAGE)
    private String userEmail;

    @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_TOO_LONG_MESSAGE)
    private String description;

    @NotNull(message = DEVELOPER_ROL_IS_NULL_MESSAGE)
    private DeveloperRolEnum developerRol;

    private List<Long> badges;
    private Map<SocialMediaEnum, String> socialMedia;
    private List<Long> hobbies;
    private StaffRolEnum staffRol;
}
