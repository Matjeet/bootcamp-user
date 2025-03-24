package com.example.bootcamp.user.domain.model;

public class Country {

    private Long id;

    public Country(Long id) {
        this.id = id;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
