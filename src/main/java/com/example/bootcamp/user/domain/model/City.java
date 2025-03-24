package com.example.bootcamp.user.domain.model;

public class City {

    private Long id;
    private Department department;

    public City(Long id, Department department) {
        this.id = id;
        this.department = department;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
