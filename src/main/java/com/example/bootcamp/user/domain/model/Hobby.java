package com.example.bootcamp.user.domain.model;

public class Hobby {

    private Long id;
    private String name;

    public Hobby(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hobby() {
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
