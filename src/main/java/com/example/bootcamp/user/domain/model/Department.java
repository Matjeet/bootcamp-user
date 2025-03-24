package com.example.bootcamp.user.domain.model;

public class Department {

    private Long id;
    private Country country;

    public Department(Long id, Country country) {
        this.id = id;
        this.country = country;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
