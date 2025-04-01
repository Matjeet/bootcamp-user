package com.example.bootcamp.user.ports.driven.dynamodb.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class ProfileEntity {


    private String userEmail;
    private String description;
    private String developerRol;
    private List<String> badges;
    private Map<String, String> socialMedia;
    private List<String> hobbies;
    private String staffRol;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_email")
    public String getUserEmail() {
        return userEmail;
    }

    @DynamoDbAttribute(value = "description")
    public String getDescription() {
        return description;
    }

    @DynamoDbAttribute(value = "developer_rol")
    public String getDeveloperRol() {
        return developerRol;
    }

    @DynamoDbAttribute(value = "badges")
    public List<String> getBadges() {
        return badges;
    }

    @DynamoDbAttribute(value = "social_media")
    public Map<String, String> getSocialMedia() {
        return socialMedia;
    }

    @DynamoDbAttribute(value = "hobbies")
    public List<String> getHobbies() {
        return hobbies;
    }

    @DynamoDbAttribute(value = "staff_rol")
    public String getStaffRol() {
        return staffRol;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeveloperRol(String developerRol) {
        this.developerRol = developerRol;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public void setSocialMedia(Map<String, String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setStaffRol(String staffRol) {
        this.staffRol = staffRol;
    }
}
