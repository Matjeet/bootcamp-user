package com.example.bootcamp.user.domain.model;

public class Location {

    private Long id;
    private Long cityId;
    private Long institutionId;
    private Long studentId;

    public Location(Long id, Long cityId, Long institutionId, Long studentId) {
        this.id = id;
        this.cityId = cityId;
        this.institutionId = institutionId;
        this.studentId = studentId;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
