package com.example.bootcamp.user.domain.model;

public class Institution {

    private Long id;
    private String name;
    private String nit;
    private String contact;

    public Institution(Long id, String name, String nit, String contact) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.contact = contact;
    }

    public Institution() {
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
