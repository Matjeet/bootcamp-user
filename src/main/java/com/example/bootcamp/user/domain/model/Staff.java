package com.example.bootcamp.user.domain.model;

public class Staff {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private DeveloperRol developerRol;
    private StaffRol staffRol;

    public Staff(Long id, String name, String lastName, String email, DeveloperRol developerRol, StaffRol staffRol) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.developerRol = developerRol;
        this.staffRol = staffRol;
    }

    public Staff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DeveloperRol getDeveloperRol() {
        return developerRol;
    }

    public void setDeveloperRol(DeveloperRol developerRol) {
        this.developerRol = developerRol;
    }

    public StaffRol getStaffRol() {
        return staffRol;
    }

    public void setStaffRol(StaffRol staffRol) {
        this.staffRol = staffRol;
    }
}
