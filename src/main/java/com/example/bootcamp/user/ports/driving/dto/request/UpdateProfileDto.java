package com.example.bootcamp.user.ports.driving.dto.request;

import com.example.bootcamp.user.domain.util.SocialMediaEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.example.bootcamp.user.domain.util.StudentConstants.DESCRIPTION_MAX_LENGTH;
import static com.example.bootcamp.user.domain.util.StudentMessage.DESCRIPTION_TOO_LONG_MESSAGE;

@Data
public class UpdateProfileDto {

    @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_TOO_LONG_MESSAGE)
    private String description;

    private List<Long> badges;
    private Map<SocialMediaEnum, String> socialMedia;
    private List<Long> hobbies;
}
