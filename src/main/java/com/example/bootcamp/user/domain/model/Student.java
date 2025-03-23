package com.example.bootcamp.user.domain.model;

import java.time.LocalDate;

public class Student {

    private Long id;
    private String email;
    private String name;
    private String lastname;
    private String cellphone;
    private String identification;
    private String identificationType;
    private LocalDate birthDate;
    private EducationLevel educationLevel;
    private DeveloperRol developerRol;
    private Source courseDiscoverySource;

    public Student(Long id, String email, String name, String lastname, String cellphone, String identification, String identificationType, LocalDate birthDate, EducationLevel educationLevel, DeveloperRol developerRol, Source courseDiscoverySource) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.cellphone = cellphone;
        this.identification = identification;
        this.identificationType = identificationType;
        this.birthDate = birthDate;
        this.educationLevel = educationLevel;
        this.developerRol = developerRol;
        this.courseDiscoverySource = courseDiscoverySource;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public DeveloperRol getDeveloperRol() {
        return developerRol;
    }

    public void setDeveloperRol(DeveloperRol developerRol) {
        this.developerRol = developerRol;
    }

    public Source getCourseDiscoverySource() {
        return courseDiscoverySource;
    }

    public void setCourseDiscoverySource(Source courseDiscoverySource) {
        this.courseDiscoverySource = courseDiscoverySource;
    }
}
