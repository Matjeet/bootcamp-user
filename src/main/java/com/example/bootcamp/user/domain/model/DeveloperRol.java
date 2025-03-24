package com.example.bootcamp.user.domain.model;

public class DeveloperRol {

    private Long id;
    private String name;

    public DeveloperRol(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeveloperRol() {
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
}
