package com.example.bootcamp.user.domain.model;

public class Badge {

    private Long id;
    private String name;

    public Badge(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Badge() {
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
