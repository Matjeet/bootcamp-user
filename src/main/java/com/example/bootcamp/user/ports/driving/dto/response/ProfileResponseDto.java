package com.example.bootcamp.user.ports.driving.dto.response;

import com.example.bootcamp.user.domain.util.DeveloperRolEnum;
import com.example.bootcamp.user.domain.util.SocialMediaEnum;
import com.example.bootcamp.user.domain.util.StaffRolEnum;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProfileResponseDto {

    private String userEmail;
    private String description;
    private DeveloperRolEnum developerRol;
    private List<String> badges;
    private Map<SocialMediaEnum, String> socialMedia;
    private List<String> hobbies;
    private StaffRolEnum staffRol;
}
