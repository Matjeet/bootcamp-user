package com.example.bootcamp.user.domain.model;

public class Location {

    private Long id;
    private Long cityId;
    private String address;
    private String addressDetails;

    public Location(Long id, Long cityId, String address, String addressDetails) {
        this.id = id;
        this.cityId = cityId;
        this.address = address;
        this.addressDetails = addressDetails;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }
}
