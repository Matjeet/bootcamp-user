package com.example.bootcamp.user.domain.model;

public class Hobbie {

    private Long id;
    private String name;

    public Hobbie(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hobbie() {
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
