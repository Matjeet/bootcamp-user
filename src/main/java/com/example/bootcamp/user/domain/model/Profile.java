package com.example.bootcamp.user.domain.model;

import java.util.List;
import java.util.Map;

public class Profile {

    private String id;
    private String userEmail;
    private String description;
    private String developerRol;
    private List<Badge> badges;
    private Map<String, String> socialMedia;
    private List<Hobby> hobbies;
    private String staffRol;

    public Profile(String id, String userEmail, String description, String developerRol, List<Badge> badges, Map<String, String> socialMedia, List<Hobby> hobbies, String staffRol) {
        this.id = id;
        this.userEmail = userEmail;
        this.description = description;
        this.developerRol = developerRol;
        this.badges = badges;
        this.socialMedia = socialMedia;
        this.hobbies = hobbies;
        this.staffRol = staffRol;
    }

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloperRol() {
        return developerRol;
    }

    public void setDeveloperRol(String developerRol) {
        this.developerRol = developerRol;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public Map<String, String> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(Map<String, String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getStaffRol() {
        return staffRol;
    }

    public void setStaffRol(String staffRol) {
        this.staffRol = staffRol;
    }
}